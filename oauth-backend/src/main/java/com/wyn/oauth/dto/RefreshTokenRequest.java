package com.wyn.oauth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RefreshTokenRequest(@JsonProperty("refresh_token") String refreshToken) {
}
