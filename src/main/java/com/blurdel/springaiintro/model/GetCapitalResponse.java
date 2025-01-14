package com.blurdel.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(@JsonPropertyDescription("The city name") String answer) {
}
