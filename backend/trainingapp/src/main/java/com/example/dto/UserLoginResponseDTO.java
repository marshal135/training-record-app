package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザーログイン後に返却されるレスポンスDTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginResponseDTO {
	private Integer userId;
	private String username;
	private String email;
}
