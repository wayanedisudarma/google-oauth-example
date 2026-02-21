package com.wyn.oauth.services;

import com.wyn.oauth.entity.UserEntity;
import io.jsonwebtoken.Claims;

public interface JwtService {

	String generateAccessToken(UserEntity user);

	String generateRefreshToken(UserEntity user);

	Claims parse(String token);

	String extractUserId(String token);

	boolean validateToken(String token);

}
