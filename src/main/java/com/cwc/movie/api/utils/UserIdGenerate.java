package com.cwc.movie.api.utils;

import java.util.UUID;

public class UserIdGenerate {

	public static String generateId() {
		String userId = UUID.randomUUID().toString();
		return userId;
	}
}
