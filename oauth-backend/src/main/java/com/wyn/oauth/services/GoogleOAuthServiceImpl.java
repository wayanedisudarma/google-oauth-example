package com.wyn.oauth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.wyn.oauth.dto.GoogleTokenResponse;
import com.wyn.oauth.dto.LoginRequest;

@Service
public class GoogleOAuthServiceImpl implements GoogleOAuthService {

  @Value("${google.client-id}")
  private String clientId;

  @Value("${google.client-secret}")
  private String clientSecret;

  @Value("${google.redirect-uri}")
  private String redirectUri;

  private final RestTemplate restTemplate = new RestTemplate();

  @Override
  public GoogleTokenResponse exchangeCode(LoginRequest loginRequest) {
    String url = "https://oauth2.googleapis.com/token";

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("client_id", clientId);
    params.add("client_secret", clientSecret);
    params.add("code", loginRequest.code());
    params.add("code_verifier", loginRequest.codeVerifier());
    params.add("grant_type", "authorization_code");
    params.add("redirect_uri", redirectUri);

    return restTemplate.postForObject(url, params, GoogleTokenResponse.class);
  }
}
