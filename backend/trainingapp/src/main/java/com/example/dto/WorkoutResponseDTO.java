	package com.example.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Workoutの一覧表示などで使用するレスポンス用DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WorkoutResponseDTO {
	private Integer workoutId;
	private String name;
	private String category;
	private Double weight;
	private Integer sets;
	private Integer reps;
	private LocalDate workoutDate;
}
