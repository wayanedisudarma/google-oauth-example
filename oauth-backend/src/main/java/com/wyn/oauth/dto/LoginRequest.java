package com.wyn.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginRequest(String code, @JsonProperty("code_verifier") String codeVerifier) {}
