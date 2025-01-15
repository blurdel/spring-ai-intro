package com.blurdel.springaiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(@JsonPropertyDescription("The city name") String city,
                                         @JsonPropertyDescription("The city population") Integer population,
                                         @JsonPropertyDescription("The region the city is located in") String region,
                                         @JsonPropertyDescription("The primary language spoken") String language,
                                         @JsonPropertyDescription("The currency used") String currency) {
}
