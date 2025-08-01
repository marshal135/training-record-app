package com.example.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザー情報を扱うEntityクラス
 * 対応テーブル：users
 * 
 * フィールド
 * userid：主キー
 * username:ユーザ名
 * email:メールアドレス
 * createAt:登録日時
 * 
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
	//ユーザID(主キー)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	//ユーザ名
	@Column(nullable = false, length = 50)
	private String username;
	//メールアドレス 
	@Column(nullable = false, length = 100)
	private String email;
	//パスワードのハッシュ値
	@Column(nullable = false, length = 255)
	private String password;
	//登録日時 
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

}
