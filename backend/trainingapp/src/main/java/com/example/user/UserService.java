package com.example.user;

import java.util.List;

/**
 * Userに関するインターフェース
 * 
 * 実装クラスはUserServiceImplが担当
 */
public interface UserService {
	List<User> findAllusers();
	User findUser(Integer id);
	User saveUser(User user);
	void deleteUser(Integer id);
	
	//ログイン機能用
	User findByEmail(String email);
	User loginAndGetUser(String email,String passwaord);	
}
