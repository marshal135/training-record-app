package com.example.exercisemaster;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ExerciseDTO;
import com.example.dto.ExerciseRequestDTO;

/**
 * ExerciseMasterに関するAPIエンドポイントを提供するコントローラークラス
 * 
 * 種目の登録・取得・更新・削除などを担当
 * DTOを使った種目名の重複チェック付き登録も含む
 * 
 */
@RestController
@RequestMapping("/exercisemaster")
public class ExerciseMasterController {

	private final ExerciseMasterService exerciseMasterService;

	@Autowired
	public ExerciseMasterController(ExerciseMasterService exerciseMasterService) {
		this.exerciseMasterService = exerciseMasterService;
	}

	//全件取得
	@GetMapping
	public List<ExerciseMaster> getAllExerciseMasters() {
		return exerciseMasterService.findAllExerciseMasters();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExerciseMaster> getExerciseMasterByid(@PathVariable Integer id) {
		ExerciseMaster exerciseMaster = exerciseMasterService.findExerciseMaster(id);
		if (exerciseMaster == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(exerciseMaster);
	}

	//新規登録
	@PostMapping
	public ExerciseMaster createExerciseMaster(@RequestBody ExerciseMaster exerciseMaster) {
		return exerciseMasterService.saveExerciseMaster(exerciseMaster);
	}

	//更新
	@PutMapping("/{id}")
	public ResponseEntity<ExerciseMaster> updateExerciseMaster(@PathVariable Integer id,
			@RequestBody ExerciseMaster exerciseMaster) {
		ExerciseMaster exisiting = exerciseMasterService.findExerciseMaster(id);
		if (exisiting == null) {
			return ResponseEntity.notFound().build();
		}
		exerciseMaster.setExerciseId(id);
		ExerciseMaster updated = exerciseMasterService.saveExerciseMaster(exerciseMaster);
		return ResponseEntity.ok(updated);
	}

	//削除
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteExerciseMaster(@PathVariable Integer id) {
		ExerciseMaster existing = exerciseMasterService.findExerciseMaster(id);
		if (existing == null) {
			return ResponseEntity.notFound().build();

		}
		exerciseMasterService.deleteExerciseMaster(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/exercises")
	public List<ExerciseDTO> getAllExercises() {
		return exerciseMasterService.getAllExercises();
	}

	@PostMapping("/exercise")
	public ResponseEntity<ExerciseMaster> createExercise(@RequestBody @Valid ExerciseRequestDTO dto) {
		ExerciseMaster result = exerciseMasterService.saveIfNotExists(dto);
		return ResponseEntity.ok(result);
	}

}
