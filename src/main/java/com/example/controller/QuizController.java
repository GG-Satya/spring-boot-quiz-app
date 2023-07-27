package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Quiz;
import com.example.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numOfQuestion,@RequestParam String title){
		return quizService.createQuiz(category,numOfQuestion,title);
	}
	
	@GetMapping("/getAllQuiz")
	public ResponseEntity<List<Quiz>> getAllQuiz(){
		return quizService.getAllQuiz();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuiz(@PathVariable Long id){
		return quizService.deleteQuiz(id);
	}
}
