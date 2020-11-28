package com.voice.authentication.service;


import com.voice.authentication.models.RegisterResponse;
import com.voice.authentication.models.VerifyVoiceResponse;
import com.voice.authentication.models.VoiceEnrollRequest;
import com.voice.authentication.models.VoiceEnrollResponse;
import com.voice.authentication.repository.EnrollmentRepository;
import com.voice.authentication.repository.VoiceLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoiceLoginService {

    private final VoiceLoginRepository voiceLoginRepository;

    private final EnrollmentRepository enrollmentRepository;

    public RegisterResponse register(){


        RegisterResponse registerResponse = voiceLoginRepository.register();
        return registerResponse;
    }

    public VoiceEnrollResponse enroll(VoiceEnrollRequest voiceEnrollRequest){

        voiceEnrollRequest.setContentLanguage("en-US");
        VoiceEnrollResponse voiceEnrollResponse = enrollmentRepository.enroll(voiceEnrollRequest);
        return voiceEnrollResponse;
    }

    public VoiceEnrollResponse enrollVoice(VoiceEnrollRequest voiceEnrollRequest){

        voiceEnrollRequest.setContentLanguage("en-US");
        VoiceEnrollResponse voiceEnrollResponse = enrollmentRepository.enrollVoice(voiceEnrollRequest);
        return voiceEnrollResponse;
    }


    public VerifyVoiceResponse verify(VoiceEnrollRequest voiceEnrollRequest){

        voiceEnrollRequest.setContentLanguage("en-US");
        VerifyVoiceResponse verifyVoiceResponse = enrollmentRepository.verify(voiceEnrollRequest);
        return verifyVoiceResponse;
    }
}
