package com.voice.authentication.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.voice.authentication.models.VerifyVoiceResponse;
import com.voice.authentication.models.VoiceEnrollRequest;
import com.voice.authentication.models.VoiceEnrollResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class EnrollmentRepository {
    public static final String VERSION = "1.6.0";
    public static final String API_KEY = System.getenv("VOICE_API_KEY");
    public static final String API_TOKEN = System.getenv("VOICE_API_TOKEN");
    public static final String API_BASE_URL = System.getenv("VOICE_API_BASE_URL");

    public VoiceEnrollResponse enroll(VoiceEnrollRequest voiceEnrollRequest) {
        VoiceEnrollResponse responseEntity = null;
        HttpClient httpClient = null;
        try {
            HttpClientBuilder clientBuilder = HttpClientBuilder.create();
            setup(clientBuilder, API_KEY, API_TOKEN);
            httpClient = clientBuilder.build();


            String baseUrl = API_BASE_URL+"/enrollments/voice";

            File convFile = convert(voiceEnrollRequest.getRecording());

            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addTextBody("userId", voiceEnrollRequest.getUserId())
                    .addTextBody("contentLanguage", voiceEnrollRequest.getContentLanguage())
                    .addTextBody("phrase", voiceEnrollRequest.getPhrase(), ContentType.create("text/plain", Charset.forName("UTF-8")))
                    .addBinaryBody("recording", convFile, ContentType.create("application/octet-stream"), "recording")
                    .build();
            HttpPost httpPost = new HttpPost(baseUrl);
            httpPost.setEntity(entity);

            try {
                String s = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
                ObjectMapper obj = new ObjectMapper();
                responseEntity = obj.readValue(s, VoiceEnrollResponse.class);
                System.out.println(s);
            } catch (Exception e) {
                e.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    public VoiceEnrollResponse enrollVoice(VoiceEnrollRequest voiceEnrollRequest) {
        VoiceEnrollResponse responseEntity = null;
        HttpClient httpClient = null;
        try {
            HttpClientBuilder clientBuilder = HttpClientBuilder.create();
            setup(clientBuilder, "apiKey", "apiToken");
            httpClient = clientBuilder.build();


            String baseUrl = API_BASE_URL+"/enrollments/voice";


            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addTextBody("userId", voiceEnrollRequest.getUserId())
                    .addTextBody("contentLanguage", voiceEnrollRequest.getContentLanguage())
                    .addTextBody("phrase", voiceEnrollRequest.getPhrase(), ContentType.create("text/plain", Charset.forName("UTF-8")))
                    .addTextBody("recording", voiceEnrollRequest.getFile())
                    .build();
            HttpPost httpPost = new HttpPost(baseUrl);
            httpPost.setEntity(entity);

            try {
                String s = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
                ObjectMapper obj = new ObjectMapper();
                responseEntity = obj.readValue(s, VoiceEnrollResponse.class);
                System.out.println(s);
            } catch (Exception e) {
                e.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    private void setup(HttpClientBuilder clientBuilder, String apiKey, String apiToken) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(apiKey, apiToken));
        clientBuilder
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultHeaders(Arrays.asList(new BasicHeader("platformId", "29"), new BasicHeader("platformVersion", VERSION)));
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public VerifyVoiceResponse verify(VoiceEnrollRequest voiceEnrollRequest) {
        VerifyVoiceResponse responseEntity = null;
        HttpClient httpClient = null;
        try {
            HttpClientBuilder clientBuilder = HttpClientBuilder.create();
            setup(clientBuilder,API_KEY, API_TOKEN);
            httpClient = clientBuilder.build();


            String baseUrl = API_BASE_URL+"/verification/voice";

            File convFile = convert(voiceEnrollRequest.getRecording());

            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addTextBody("userId", voiceEnrollRequest.getUserId())
                    .addTextBody("contentLanguage", voiceEnrollRequest.getContentLanguage())
                    .addTextBody("phrase", voiceEnrollRequest.getPhrase(), ContentType.create("text/plain", Charset.forName("UTF-8")))
                    .addBinaryBody("recording", convFile, ContentType.create("application/octet-stream"), "recording")
                    .build();
            HttpPost httpPost = new HttpPost(baseUrl);
            httpPost.setEntity(entity);

            try {
                String s = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
                ObjectMapper obj = new ObjectMapper();
                responseEntity = obj.readValue(s, VerifyVoiceResponse.class);
                System.out.println(s);
            } catch (Exception e) {
                e.getMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
