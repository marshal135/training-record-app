package com.example.workout;

import java.util.List;

import com.example.dto.WorkoutRequestDTO;
import com.example.dto.WorkoutResponseDTO;

/**
 * workoutに関するインターフェース
 * 
 * 実装クラスはWorkoutServiceImplが担当
 */

public interface WorkoutService {
	List<Workout> findAllWorkouts();

	Workout findWorkout(Integer id);
	//全てのWorkoutエンティティを取得
	Workout saveWorkout(Workout workout);
	//指定IDのWorkoutを1件取得
	void deleteWorkout(Integer id);
	//Workoutエンティティの削除
	void saveWorkout(WorkoutRequestDTO dto);
	//Workoutエンティティの保存
	
	/**
	 * 特定のユーザIDと年月に該当するWorkoutをDTO形式で取得する
	 * 
	 * @param UserId　ユーザID
	 * @param year　対象年
	 * @param month　対象月
	 * @return　WorkoutResponseDTOのリスト
	 */
	List<WorkoutResponseDTO> getWorkoutsByUserIdAndMonth(Integer UserId, int year, int month);
}
