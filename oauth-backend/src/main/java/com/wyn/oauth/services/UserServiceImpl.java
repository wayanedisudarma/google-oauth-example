package com.wyn.oauth.services;

import org.springframework.stereotype.Service;

import com.wyn.oauth.dto.GetUserResponse;
import com.wyn.oauth.entity.UserEntity;
import com.wyn.oauth.enumeration.ErrorCodes;
import com.wyn.oauth.exception.DataNotFoundException;
import com.wyn.oauth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public GetUserResponse getUserById(String userId) {
    UserEntity user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new DataNotFoundException(ErrorCodes.USER_NOT_FOUND.name()));
    return GetUserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .name(user.getName())
        .build();
  }
}
