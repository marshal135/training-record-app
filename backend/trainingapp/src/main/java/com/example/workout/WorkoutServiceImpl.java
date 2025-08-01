package com.example.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.WorkoutRequestDTO;
import com.example.dto.WorkoutResponseDTO;
import com.example.exercisemaster.ExerciseMaster;
import com.example.exercisemaster.ExerciseMasterRepository;
import com.example.user.User;
import com.example.user.UserRepository;

/**
 * 
 * Workoutに関するビジネスロジックの実装クラス
 * 
 * workoutの登録(DTO→entity)
 * workoutの取得
 * workoutの削除
 * 
 * 必要に応じてUserやExerciseMasterとのリレーションも連携
 * 
 */
@Service
public class WorkoutServiceImpl implements WorkoutService {

	private final ExerciseMasterRepository exerciseMasterRepository;
	private final WorkoutRepository workoutRepository;
	private final UserRepository userRepository;

	@Autowired
	public WorkoutServiceImpl(
			WorkoutRepository workoutRepository,
			ExerciseMasterRepository exerciseMasterRepository,
			UserRepository userRepository) {
		this.workoutRepository = workoutRepository;
		this.exerciseMasterRepository = exerciseMasterRepository;
		this.userRepository = userRepository;
	}

	/**
	 *Workoutを全件取得
	 */
	@Override
	public List<Workout> findAllWorkouts() {
		return this.workoutRepository.findAll();
	}

	/**
	 *特定のIDのWorkoutを取得
	 */
	@Override
	public Workout findWorkout(Integer id) {
		Optional<Workout> workoutOptional = workoutRepository.findById(id);
		return workoutOptional.orElse(null);
	}

	/**
	 *Workoutの記録を保存
	 */
	@Override
	public Workout saveWorkout(Workout workout) {
		return workoutRepository.save(workout);
	}

	/**
	 *特定のIDのWorkoutを削除
	 */
	@Override
	public void deleteWorkout(Integer id) {
		workoutRepository.deleteById(id);
	}

	/**
	 *WotkoutResponseDTOからWorkoutエンティティを生成し保存する
	 */
	@Override
	public void saveWorkout(WorkoutRequestDTO dto) {
		// ExerciseMasterの取得
		Optional<ExerciseMaster> exerciseOpt = exerciseMasterRepository.findById(dto.getExerciseId());
		if (!exerciseOpt.isPresent()) {
			throw new IllegalArgumentException("指定された種目が存在しません");
		}
		ExerciseMaster exercise = exerciseOpt.get();

		// Userの取得
		Optional<User> userOpt = userRepository.findById(dto.getUserId());
		if (!userOpt.isPresent()) {
			throw new IllegalArgumentException("指定されたユーザーが存在しません");
		}
		User user = userOpt.get();

		// Workoutエンティティ作成
		Workout workout = new Workout();
		workout.setUser(user); // ← user_id のセット
		workout.setExercise(exercise);
		workout.setWeight(dto.getWeight());
		workout.setSets(dto.getSets());
		workout.setReps(dto.getReps());
		workout.setWorkoutDate(dto.getDate());

		// 保存
		workoutRepository.save(workout);
	}

	/**
	 *　指定ユーザー・年月ん該当するWorkoutを取得し、DTO形式で返す
	 * @param userId ユーザーID
	 * @param year 対象年
	 * @param month 対象月
	 * @return WorkoutResponseDTOのリスト	 
	 */
	@Override
	public List<WorkoutResponseDTO> getWorkoutsByUserIdAndMonth(Integer userId, int year, int month) {
		List<Workout> workouts = workoutRepository.findByUserIdAndYearAndMonth(userId, year, month);

		List<WorkoutResponseDTO> dtoList = new ArrayList<>();
		for (Workout w : workouts) {
			WorkoutResponseDTO dto = new WorkoutResponseDTO(
					w.getWorkoutId(),
					w.getExercise().getName(),
					w.getExercise().getCategory(),
					w.getWeight(),
					w.getSets(),
					w.getReps(),
					w.getWorkoutDate());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
