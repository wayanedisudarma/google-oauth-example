package com.wyn.oauth.services;

import com.wyn.oauth.dto.GetUserResponse;

public interface UserService {

  GetUserResponse getUserById(String userId);
}
