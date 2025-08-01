package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * アプリ全体の共通クラス
 * 
 * CORSの許可やパスワードのハッシュ化を担当
 */
@Configuration

public class WebConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOriginPatterns("http://localhost:5173")
						// 開発時（Viteがlocalhost:5173で動く）
						// 本番環境でAPIを公開する場合は適宜ドメインを追加
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowCredentials(true); // ← これは allowedOriginPatterns とセットで使える
			}
		};
	}

	/**
	 * パスワードのハッシュ化に使用する
	 * 
	 * @return　登録したパスワードをハッシュ化した値
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
