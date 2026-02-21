package com.wyn.oauth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import com.wyn.oauth.dto.GoogleTokenResponse;
import com.wyn.oauth.dto.LoginRequest;
import com.wyn.oauth.dto.LoginResponse;
import com.wyn.oauth.dto.RefreshTokenRequest;
import com.wyn.oauth.dto.RefreshTokenResponse;
import com.wyn.oauth.entity.UserEntity;
import com.wyn.oauth.repository.UserRepository;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final GoogleOAuthService googleOAuthService;

  private final UserRepository userRepository;

  private final JwtService jwtService;

  private final JwtDecoder googleJwtDecoder;

  @Value("${google.client-id}")
  private String clientId;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    GoogleTokenResponse tokenResponse = googleOAuthService.exchangeCode(loginRequest);

    Jwt jwt = googleJwtDecoder.decode(tokenResponse.idToken());

    String email = jwt.getClaim("email");
    String name = jwt.getClaim("name");
    String issuer = jwt.getIssuer().toString();
    String audience = jwt.getAudience().getFirst();

    if (!issuer.equals("https://accounts.google.com")) {
      throw new RuntimeException("Invalid issuer");
    }

    if (!audience.equals(clientId)) {
      throw new RuntimeException("Invalid audience");
    }

    UserEntity user =
        userRepository
            .findByEmail(email)
            .orElseGet(() -> userRepository.save(new UserEntity(null, email, name)));

    String accessToken = jwtService.generateAccessToken(user);
    String refreshToken = jwtService.generateRefreshToken(user);
    return new LoginResponse(accessToken, refreshToken);
  }

  @Override
  public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

    Claims claims = jwtService.parse(refreshTokenRequest.refreshToken());

    if (!"refresh".equals(claims.get("type"))) {
      throw new RuntimeException("Invalid refresh token");
    }

    String userId = claims.getSubject();
    UserEntity userEntity = userRepository.findById(userId).orElseThrow();

    String newAccessToken = jwtService.generateAccessToken(userEntity);

    return new RefreshTokenResponse(newAccessToken, refreshTokenRequest.refreshToken());
  }
}
