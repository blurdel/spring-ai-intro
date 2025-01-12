package com.blurdel.springaiintro.services;

import com.blurdel.springaiintro.model.Answer;
import com.blurdel.springaiintro.model.GetCapitalRequest;
import com.blurdel.springaiintro.model.Question;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getCapital(GetCapitalRequest getCapitalRequest);
    Answer getAnswer(Question question);
}
