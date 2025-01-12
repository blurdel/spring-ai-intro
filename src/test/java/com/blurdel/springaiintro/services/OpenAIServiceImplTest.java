package com.blurdel.springaiintro.services;

import com.blurdel.springaiintro.model.Answer;
import com.blurdel.springaiintro.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService service;

    @Test
    void getAnswer() {
        Answer answer = service.getAnswer(new Question("4 + 4 = ?"));
        System.out.println("Got the answer");
        System.out.println(answer);
    }

}