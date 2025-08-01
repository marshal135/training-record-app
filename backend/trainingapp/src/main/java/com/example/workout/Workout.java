package com.example.workout;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.example.exercisemaster.ExerciseMaster;
import com.example.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Workoutエンティティ
 * 
 * ユーザーが記録したトレーニングに内容を表す
 * 対応テーブル：workouts
 * 
 * 各レコードはユーザーと種目に関連つけられており、日付・重量・回数などの情報を持つ。
 */
@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Workout {
	
	/**
	 * Workoutの主キー
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workout_id")
	private Integer workoutId;

	/**
	 * Workoutを記録したユーザー
	 */
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "exercise_id", nullable = false)
	private ExerciseMaster exercise;

	@Column(name = "workout_date")
	private LocalDate workoutDate;

	/**
	 * 1セット当たりの回数
	 */
	private Integer reps;

	private Integer sets;

	private Double weight;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

}