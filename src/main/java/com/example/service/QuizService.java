package com.example.service;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Question;
import com.example.model.QuestionWrapper;
import com.example.model.Quiz;
import com.example.model.QuizAnsResponse;
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

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(long id) {
		List<QuestionWrapper> questionWrapperList = new ArrayList<QuestionWrapper>();
		Quiz quiz = quizRepository.findById(id).orElse(null);
		if(quiz != null) {
			List<Question> QuestionList = quiz.getQuestionList();
			for(Question question : QuestionList) {
				QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
				questionWrapperList.add(questionWrapper);
			}
			return new ResponseEntity<List<QuestionWrapper>>(questionWrapperList, HttpStatus.OK);
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionWrapperList, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> calculateResult(long id, List<QuizAnsResponse> quizAnsResponseList) {
		int result = 0;
			for(QuizAnsResponse response : quizAnsResponseList) {
				Question question = questionRepository.findById(response.getId()).orElse(null);
				if(question != null ){
					String correctAnswer = question.getRightAnswer();
					String enteredAnswer =  response.getEnteredAnswer();
					if(enteredAnswer.equals(correctAnswer))result++;
				}
			}
		return new ResponseEntity<String>("Your Score is "+result ,HttpStatus.OK);
	}

}
