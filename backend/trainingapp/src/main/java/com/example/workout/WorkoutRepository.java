package com.example.workout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Workoutエンティティに対するDB操作を提供するリポジトリインターフェース
 * 
 * 標準的なCRUD操作はJPArepositoryによって自動提供
 */
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
	@Query("""
			SELECT w FROM Workout w
			JOIN FETCH w.exercise
			WHERE w.user.userId = :userId
			AND FUNCTION('YEAR', w.workoutDate) = :year
			AND FUNCTION('MONTH', w.workoutDate) = :month
		""")
		//種目情報も一緒に取得
	
		List<Workout> findByUserIdAndYearAndMonth(
			@Param("userId") Integer userId,
			@Param("year") int year,
			@Param("month") int month
		);

}