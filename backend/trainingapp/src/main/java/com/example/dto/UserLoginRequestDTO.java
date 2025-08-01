package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザーログイン時のリクエストデータをまとめるDTO
 * 
 * フロントエンドからemailとパスワードを受け取る
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginRequestDTO {
	private String email;
	private String password;
}
