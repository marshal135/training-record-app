package com.example.workout;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.WorkoutRequestDTO;
import com.example.dto.WorkoutResponseDTO;

/**
 * Workoutに関するAPIエンドポイントを提供するコントローラークラス
 * 
 * 提供機能：
 *・Workoutの登録（POST）
 *・一覧取得（日付・月単位）（GET）
 *・個別取得（GET）
 *・更新（PUT）
 *・削除（DELETE）
 * 
 */
@RestController
@RequestMapping("/workout")
public class WorkoutController {
	private final WorkoutService workoutService;

	@Autowired
	public WorkoutController(WorkoutService workoutService) {
		this.workoutService = workoutService;
	}

	/**
	 * 
	 * ログインしたユーザのworkout一覧を取得する
	 * 
	 * @param userId　ユーザID
	 * @param year　対象の年
	 * @param month　対象の月
	 * @return　workoutのレスポンスDTOリスト
	 */
	@GetMapping
	public List<WorkoutResponseDTO> getAllworkout(
			@RequestParam Integer userId,
			@RequestParam int year,
			@RequestParam int month) {
		return workoutService.getWorkoutsByUserIdAndMonth(userId, year, month);
	}

	/**
	 * 特定のIDのworkoutを取得する
	 * @param id　WorkoutのID
	 * @return　workoutのデータ(トレーニング記録)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Workout> getWorkoutByid(@PathVariable Integer id) {
		Workout workout = workoutService.findWorkout(id);
		if (workout == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(workout);
	}

	/**
	 * 現在は未使用。フロント側で読んでいないためコメントアウト
	 * 
	@GetMapping("/workout/monthly")
	public List<WorkoutResponseDTO> getMonthlyWorkouts(
			@RequestParam Integer userId,
			@RequestParam int year,
			@RequestParam int month) {
		return workoutService.getWorkoutsByUserIdAndMonth(userId, year, month);
	}
	*/

	/**
	 * 現在は未使用。フロント側で読んでいないためコメントアウト
	 * 
	@PutMapping("/{id}")
	public ResponseEntity<Workout> updatedWorkout(@PathVariable Integer id, @RequestBody Workout workout) {
		Workout exisiting = workoutService.findWorkout(id);
		if (exisiting == null) {
			return ResponseEntity.notFound().build();
		}
		Workout updated = workoutService.saveWorkout(workout);
		return ResponseEntity.ok(updated);
	}
	*/

	/**
	 * 特定のworkoutの記録を削除する
	 * 
	 * @param id　workoutID
	 * @return 成功時は204 no content、失敗時は404 notfound
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWorkout(@PathVariable Integer id) {
		Workout exisiting = workoutService.findWorkout(id);
		if (exisiting == null) {
			return ResponseEntity.notFound().build();
		}
		workoutService.deleteWorkout(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 新しいworkoutを登録する
	 * 
	 * @param dto workoutの登録リクエストDTO
	 * @return　成功時は200 ok
	 */
	@PostMapping
	public ResponseEntity<Void> registerWorkout(@RequestBody @Valid WorkoutRequestDTO dto) {
		workoutService.saveWorkout(dto);
		return ResponseEntity.ok().build();
	}

}
