package com.wyn.oauth.controller;

import com.wyn.oauth.dto.GetUserResponse;
import com.wyn.oauth.dto.Response;
import com.wyn.oauth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	public Response<GetUserResponse> getUser(@AuthenticationPrincipal String userId) {
		return Response.ok(userService.getUserById(userId));
	}

}
