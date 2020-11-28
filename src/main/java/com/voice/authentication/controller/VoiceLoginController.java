package com.voice.authentication.controller;


import com.voice.authentication.api.VoiceLoginAPI;
import com.voice.authentication.models.*;
import com.voice.authentication.service.VoiceLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class VoiceLoginController implements VoiceLoginAPI {

    private final VoiceLoginService voiceLoginService;

    @Override
    public ResponseEntity<RegisterResponse> register(RegisterUserRequest registerUserRequest) throws Exception {
        RegisterResponse registerResponse = voiceLoginService.register();
        return ResponseEntity.status(HttpStatus.OK).body(registerResponse);
    }

    @Override
    public ResponseEntity<VoiceEnrollResponse> enroll(String userId, String phrase, MultipartFile file) throws Exception {

         VoiceEnrollRequest voiceEnrollRequest = VoiceEnrollRequest.builder().userId(userId).phrase(phrase).recording(file).build();
        VoiceEnrollResponse voiceEnrollResponse = voiceLoginService.enroll(voiceEnrollRequest);
        return ResponseEntity.status(HttpStatus.OK).body(voiceEnrollResponse);
    }


    @Override
    public ResponseEntity<VerifyVoiceResponse> verify(String userId, String phrase, MultipartFile file) throws Exception {
        VoiceEnrollRequest voiceEnrollRequest = VoiceEnrollRequest.builder().userId(userId).phrase(phrase).recording(file).build();
        VerifyVoiceResponse verifyVoiceResponse = voiceLoginService.verify(voiceEnrollRequest);
        return ResponseEntity.status(HttpStatus.OK).body(verifyVoiceResponse);
    }
    @Override
    public ResponseEntity<VoiceEnrollResponse> enrollVoice(VoiceEnrollRequest voiceEnrollRequest) throws Exception {

        VoiceEnrollResponse voiceEnrollResponse = voiceLoginService.enroll(voiceEnrollRequest);
        return ResponseEntity.status(HttpStatus.OK).body(voiceEnrollResponse);
    }

}
