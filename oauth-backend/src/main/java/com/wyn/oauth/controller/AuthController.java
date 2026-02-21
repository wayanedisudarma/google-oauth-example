package com.wyn.oauth.controller;

import com.wyn.oauth.dto.LoginRequest;
import com.wyn.oauth.dto.LoginResponse;
import com.wyn.oauth.dto.RefreshTokenRequest;
import com.wyn.oauth.dto.RefreshTokenResponse;
import com.wyn.oauth.dto.Response;
import com.wyn.oauth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/google")
	public Response<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = authService.login(loginRequest);
		return Response.ok(loginResponse);
	}

	@PostMapping("/refresh")
	public Response<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		RefreshTokenResponse refreshTokenResponse = authService.refreshToken(refreshTokenRequest);
		return Response.ok(refreshTokenResponse);
	}

}
