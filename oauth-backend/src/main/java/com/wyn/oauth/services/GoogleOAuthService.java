package com.wyn.oauth.services;

import com.wyn.oauth.dto.GoogleTokenResponse;
import com.wyn.oauth.dto.LoginRequest;

public interface GoogleOAuthService {

	GoogleTokenResponse exchangeCode(LoginRequest loginRequest);

}
