package com.blurdel.springaiintro.services;

import com.blurdel.springaiintro.model.Answer;
import com.blurdel.springaiintro.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
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
