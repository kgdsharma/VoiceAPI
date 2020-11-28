package com.voice.authentication.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyVoiceResponse {

    private String message;
    private String responseCode;
    private float status;
    private String text;
    private String timeTaken;
    private float textConfidence;
    private float confidence;
}
