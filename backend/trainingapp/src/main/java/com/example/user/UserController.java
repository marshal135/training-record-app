package com.example.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.UserLoginRequestDTO;
import com.example.dto.UserLoginResponseDTO;

/**
 * Userに関するAPIのエンドポイントを提供するコントローラークラス
 * 
 */
@RestController
@RequestMapping("/user")

public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginRequestDTO loginRequestDTO) {
		User user = userService.loginAndGetUser(
			loginRequestDTO.getEmail(),
			loginRequestDTO.getPassword()
		);

		if (user != null) {
			UserLoginResponseDTO responseDTO = new UserLoginResponseDTO(
				user.getUserId(),
				user.getUsername(),
				user.getEmail()
			);
			return ResponseEntity.ok(responseDTO);
		} else {
			return ResponseEntity.status(401).body("メールアドレスまたはパスワードが間違っています");
		}
	}


	//全件取得
	@GetMapping
	public List<User> getAllUsers() {
		return userService.findAllusers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserByid(@PathVariable Integer id) {
		User user = userService.findUser(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	//新規登録
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	//更新
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
		User exisiting = userService.findUser(id);
		if (exisiting == null) {
			return ResponseEntity.notFound().build();
		}
		user.setUserId(id);
		User updated = userService.saveUser(user);
		return ResponseEntity.ok(updated);
	}

	//削除
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
		User existing = userService.findUser(id);
		if (existing == null) {
			return ResponseEntity.notFound().build();

		}
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
