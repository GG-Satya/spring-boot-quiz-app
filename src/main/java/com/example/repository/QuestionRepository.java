package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RANDOM() Limit :numOfQuestion", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(@Param("category") String category,@Param("numOfQuestion") int numOfQuestion);
	
}
