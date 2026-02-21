package com.wyn.oauth.services;

import com.wyn.oauth.dto.LoginRequest;
import com.wyn.oauth.dto.LoginResponse;
import com.wyn.oauth.dto.RefreshTokenRequest;
import com.wyn.oauth.dto.RefreshTokenResponse;

public interface AuthService {

	LoginResponse login(LoginRequest loginRequest);

	RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
