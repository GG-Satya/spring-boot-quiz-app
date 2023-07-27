package com.example.service;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Question;
import com.example.model.Quiz;
import com.example.repository.QuestionRepository;
import com.example.repository.QuizRepository;

@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionRepository questionRepository;

	public ResponseEntity<Quiz> createQuiz(String category, int numOfQuestion, String title) {
		
		List<Question> questionList = questionRepository.findRandomQuestionsByCategory(category, numOfQuestion);
		System.err.println(questionList);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionList(questionList);
		quizRepository.save(quiz);
		
		return new ResponseEntity<Quiz>(quiz,HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteQuiz(Long id) {
		try {
			quizRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted Quiz of id = "+id,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Error while deleting Quiz of id = "+id,HttpStatus.BAD_GATEWAY);
	}

	public ResponseEntity<List<Quiz>> getAllQuiz() {
		try {
			return new ResponseEntity<List<Quiz>>(quizRepository.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Quiz>>(new ArrayList<Quiz>(),HttpStatus.BAD_GATEWAY);
	}

}
