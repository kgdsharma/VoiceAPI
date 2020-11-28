package com.voice.authentication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@Builder
public class VoiceEnrollRequest {

    @JsonProperty(value = "userId")
    private String userId;
    @JsonProperty(value = "phrase")
    private String phrase;
    @JsonProperty(value = "recording")
    private MultipartFile recording;

    private String file;

    private String contentLanguage;
}
