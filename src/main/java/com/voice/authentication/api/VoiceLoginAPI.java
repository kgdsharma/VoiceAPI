package com.voice.authentication.api;


import com.voice.authentication.models.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "/v1/user")
public interface VoiceLoginAPI {

    @CrossOrigin
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<RegisterResponse> register(RegisterUserRequest registerUserRequest) throws Exception;

    @CrossOrigin
    @PostMapping(value = "/enroll", produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<VoiceEnrollResponse> enroll(@RequestParam("userId") String userId, @RequestParam("phrase") String phrase, @RequestParam(value = "recording", required = false) MultipartFile file) throws Exception;

    @CrossOrigin
    @PostMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<VerifyVoiceResponse> verify(@RequestParam("userId") String userId, @RequestParam("phrase") String phrase, @RequestParam(value = "recording", required = false) MultipartFile file) throws Exception;

    @CrossOrigin
    @PostMapping(value = "/enrollVoice", produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract ResponseEntity<VoiceEnrollResponse> enrollVoice(VoiceEnrollRequest voiceEnrollRequest) throws Exception;
}
