package com.wyn.oauth.dto;

import lombok.Builder;

@Builder
public record GetUserResponse(String id, String email, String name) {
}
