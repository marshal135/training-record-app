package com.example.exercisemaster;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * ExerciseMasterエンティティ
 * 
 * トレーニング種目の情報を管理する
 * 対応テーブル:exercise_master
 * 
 * 各レコードはWorkoutに関連つけられている
 * 
 */
@Entity
@Table(name = "exercise_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExerciseMaster {
	//ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exercise_id")
	private Integer exerciseId;

	//トレーニング種目名
	@Column(nullable = false, length = 100)
	private String name;

	//カテゴリー(鍛える部位)
	@Column(length = 50)
	private String category;

	//トレーニングの説明
	@Column(columnDefinition = "TEXT")
	private String description;

	//登録日時 
	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

}
