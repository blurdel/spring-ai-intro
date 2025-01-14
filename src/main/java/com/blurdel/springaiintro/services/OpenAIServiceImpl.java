package com.blurdel.springaiintro.services;

import com.blurdel.springaiintro.model.Answer;
import com.blurdel.springaiintro.model.GetCapitalRequest;
import com.blurdel.springaiintro.model.GetCapitalResponse;
import com.blurdel.springaiintro.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalPromptWithInfo;

    @Autowired
    private ObjectMapper objMapper;


    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        PromptTemplate pt = new PromptTemplate(getCapitalPromptWithInfo);
        Prompt prompt = pt.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        System.out.println("Req format:\n" + format);

        PromptTemplate pt = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = pt.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                "format", format));

        ChatResponse response = chatModel.call(prompt);
        System.out.println(response.getResult().getOutput().getContent());

        return converter.convert(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getAnswer(Question question) {
        System.out.println("Called: " + question);

        PromptTemplate pt = new PromptTemplate(question.question());
        Prompt prompt = pt.create();
        ChatResponse resp = chatModel.call(prompt);

        System.out.println(resp.getResult().getOutput().getContent());
        return new Answer(resp.getResult().getOutput().getContent());
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate pt = new PromptTemplate(question);
        Prompt prompt = pt.create();
        ChatResponse resp = chatModel.call(prompt);

        return resp.getResult().getOutput().getContent();
    }

}
