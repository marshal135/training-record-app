package com.example.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 種目登録時のリクエスト用DTO
 */
@Getter
@Setter

public class ExerciseRequestDTO {
	@NotBlank
	private String name;
	@NotBlank
	private String category;
	private String description;

}
