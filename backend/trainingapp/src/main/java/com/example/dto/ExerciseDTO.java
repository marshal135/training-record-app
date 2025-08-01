package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 種目情報を画面表示用などで扱うためのDTO
 * 
 * Entityとは切り離して、必要な情報だけを保持
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExerciseDTO {
	private Integer exerciseId;
	private String name;
	private String category;
}
