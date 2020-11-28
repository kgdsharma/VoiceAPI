package com.voice.authentication.repository;


import com.voice.authentication.models.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class VoiceLoginRepository {
    public static final String API_KEY = System.getenv("VOICE_API_KEY");
    public static final String API_TOKEN = System.getenv("VOICE_API_TOKEN");
    public static final String API_BASE_URL = System.getenv("VOICE_API_BASE_URL");

    private final RestTemplate restTemplate;

    public RegisterResponse register() {
        RegisterResponse response = null;
        try {
            String baseUrl = API_BASE_URL+"/users";
            System.out.println(baseUrl);
            String authUserPass = API_KEY + ":" + API_TOKEN;
            String encodedAuthUserPass = Base64.encodeBase64String(authUserPass.getBytes());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuthUserPass);
            ResponseEntity<RegisterResponse> responseEntity;
            responseEntity = restTemplate.exchange(baseUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(httpHeaders),
                    RegisterResponse.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
