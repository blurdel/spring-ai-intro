package com.blurdel.springaiintro.services;

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
        String answer = service.getAnswer("Tell me a dad joke");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}