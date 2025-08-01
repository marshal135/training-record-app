package com.example.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Userに関するビジネスロジックの実装クラス
 * 
 * Userの登録、ログイン処理
 */
@Service

public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	//コンストラクタインジェクション(推奨)
	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public List<User> findAllusers() {
		return userRepository.findAll();
	}

	@Override
	public User findUser(Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		return userOptional.orElse(null);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	//ユーザー情報取得
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}

	//ログイン処理
	@Override
	public User loginAndGetUser(String email, String rawPassword) {
		Optional<User> userOpt = userRepository.findByEmail(email);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (passwordEncoder.matches(rawPassword, user.getPassword())) {
				return user;
			}
		}
		return null;
	}
}
