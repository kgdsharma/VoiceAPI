package com.voice.authentication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    @JsonProperty(value = "userId")
    private String userId;
}
