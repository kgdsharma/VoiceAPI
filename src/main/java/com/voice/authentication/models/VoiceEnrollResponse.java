package com.voice.authentication.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoiceEnrollResponse {

    private float textConfidence;
    private float createdAt;
    private String timeTaken;
    private String contentLanguage;
    private String text;
    private float id;
    private String message;
    private String responseCode;
    private float status;
}
