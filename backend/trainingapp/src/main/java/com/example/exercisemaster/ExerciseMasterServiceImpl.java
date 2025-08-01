package com.example.exercisemaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ExerciseDTO;
import com.example.dto.ExerciseRequestDTO;

/**
 * ExerciseMasterに関するビジネスロジックの実装クラス
 * 
 * データの取得・登録・重複チェック付き登録などをここで処理する
 */
@Service
public class ExerciseMasterServiceImpl implements ExerciseMasterService {
	private final ExerciseMasterRepository exerciseMasterRepository;

	@Autowired
	public ExerciseMasterServiceImpl(ExerciseMasterRepository exerciseMasterRepository) {
		this.exerciseMasterRepository = exerciseMasterRepository;
	}

	@Override
	public List<ExerciseMaster> findAllExerciseMasters() {
		return exerciseMasterRepository.findAll();
	}

	@Override
	public ExerciseMaster findExerciseMaster(Integer id) {
		Optional<ExerciseMaster> exerciseMasterOptional = exerciseMasterRepository.findById(id);
		return exerciseMasterOptional.orElse(null);
	}

	@Override
	public ExerciseMaster saveExerciseMaster(ExerciseMaster exerciseMaster) {
		return exerciseMasterRepository.save(exerciseMaster);
	}

	@Override
	public void deleteExerciseMaster(Integer id) {
		exerciseMasterRepository.deleteById(id);
	}

	@Override
	public List<ExerciseDTO> getAllExercises() {
		List<ExerciseMaster> exerciseList = exerciseMasterRepository.findAll();
		List<ExerciseDTO> dtoList = new ArrayList<>();

		for (ExerciseMaster e : exerciseList) {
			ExerciseDTO dto = new ExerciseDTO(e.getExerciseId(), e.getName(), e.getCategory());
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public ExerciseMaster saveIfNotExists(ExerciseRequestDTO dto) {
		Optional<ExerciseMaster> existing = exerciseMasterRepository
				.findByCategoryAndName(dto.getCategory(), dto.getName());

		if (existing.isPresent()) {
			return existing.get(); // すでに存在すればそれを返す
		}

		ExerciseMaster Em = new ExerciseMaster();
		Em.setCategory(dto.getCategory());
		Em.setName(dto.getName());
		Em.setDescription(dto.getDescription());

		return exerciseMasterRepository.save(Em);
	}

}
