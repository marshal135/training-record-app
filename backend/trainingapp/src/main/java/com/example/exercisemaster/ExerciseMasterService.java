package com.example.exercisemaster;

import java.util.List;

import com.example.dto.ExerciseDTO;
import com.example.dto.ExerciseRequestDTO;

/**
 * ExerciseMasterに関するインターフェース
 * 
 * ExerciseMasterServiceImplが実装を担当
 */
public interface ExerciseMasterService {
	List<ExerciseMaster> findAllExerciseMasters();
	ExerciseMaster findExerciseMaster(Integer id);
	ExerciseMaster saveExerciseMaster(ExerciseMaster exerciseMaster);
	void deleteExerciseMaster(Integer id);
	List<ExerciseDTO> getAllExercises();
	ExerciseMaster saveIfNotExists(ExerciseRequestDTO dto);
}
