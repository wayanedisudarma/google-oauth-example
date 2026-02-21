package com.wyn.oauth.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class Response<T> {

	private int status;

	private String message;

	private T data;

	public static <T> Response<T> ok(T data) {
		return Response.<T>builder().status(HttpStatus.OK.value()).message(HttpStatus.OK.name()).data(data).build();
	}

	public static <T> Response<T> internalServerError(String message) {
		return Response.<T>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(message).build();
	}

	public static <T> Response<T> badRequest(String message) {
		return Response.<T>builder().status(HttpStatus.BAD_REQUEST.value()).message(message).build();
	}

	public static <T> Response<T> notFound(String message) {
		return Response.<T>builder().status(HttpStatus.NOT_FOUND.value()).message(message).build();
	}

}
