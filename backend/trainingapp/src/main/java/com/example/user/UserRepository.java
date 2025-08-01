package com.example.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Userエンティティに対するDB操作を提供するリポジトリインターフェース
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email); 
	//  ログインや重複チェックで使用
}