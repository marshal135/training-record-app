package com.example.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Workoutの登録時に使用するリクエスト用DTO
 */
@Getter
@Setter

public class WorkoutRequestDTO {
	@NotNull
    private Integer userId;
	@NotNull
	private Integer exerciseId;
	@NotNull
	private Double weight;
	@NotNull
	private Integer sets;
	@NotNull
	private Integer reps;
	@NotNull
	private LocalDate date;
}
