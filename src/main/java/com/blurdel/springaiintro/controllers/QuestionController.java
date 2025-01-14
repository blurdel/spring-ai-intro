package com.blurdel.springaiintro.controllers;

import com.blurdel.springaiintro.model.Answer;
import com.blurdel.springaiintro.model.GetCapitalRequest;
import com.blurdel.springaiintro.model.Question;
import com.blurdel.springaiintro.services.OpenAIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService aiService;

    public QuestionController(OpenAIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/capitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody final GetCapitalRequest getCapitalRequest) {
        return aiService.getCapitalWithInfo(getCapitalRequest);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody final GetCapitalRequest getCapitalRequest) {
        return aiService.getCapital(getCapitalRequest);
    }

    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(@RequestBody final Question question) {
        Answer answer = aiService.getAnswer(question);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
