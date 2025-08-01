package com.example.exercisemaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ExerciseMasterエンティティに対するDB操作を提供するリポジトリインターフェース
 * 
 */
public interface ExerciseMasterRepository extends JpaRepository<ExerciseMaster, Integer> {
	Optional<ExerciseMaster> findByCategoryAndName(String category, String name);
}
