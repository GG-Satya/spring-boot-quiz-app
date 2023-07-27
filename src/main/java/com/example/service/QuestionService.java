package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Question;
import com.example.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
			return new ResponseEntity<List<Question>>(questionRepository.findAll(),HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<Question>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Question> addQuestion(Question question) {
		try {
			return new ResponseEntity<Question>(questionRepository.save(question),HttpStatus.ACCEPTED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Question>(new Question(),HttpStatus.BAD_REQUEST);
	}

	public Question updateQuestion(Question question) {
		Question existingQuestion = questionRepository.findById(question.getId()).orElse(null);
		existingQuestion.setCategory(question.getCategory());
		existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
		existingQuestion.setOption1(question.getOption1());
		existingQuestion.setOption2(question.getOption2());
		existingQuestion.setOption3(question.getOption3());
		existingQuestion.setOption4(question.getOption4());
		existingQuestion.setQuestionTitle(question.getQuestionTitle());
		existingQuestion.setRightAnswer(question.getRightAnswer());
		return questionRepository.save(existingQuestion);
	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionRepository.findByCategory(category);
	}

	public ResponseEntity<List<Question>> addAllQuestion(List<Question> questionList) {
		try {
			return new ResponseEntity<List<Question>>( questionRepository.saveAll(questionList) ,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<Question>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Question> getQuestion(Long id) {
		try {
			return new ResponseEntity<Question>(questionRepository.findById(id).orElse(null), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Question>(new Question(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> deleteQuestion(Long id) {
		try {
			questionRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted question of id "+id,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Error"+id,HttpStatus.BAD_REQUEST);
	}
	
	
}
