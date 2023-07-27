package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Question;
import com.example.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;

	@GetMapping("/getQuestion/{id}")
	public ResponseEntity<Question> getQuestion(@PathVariable Long id){
		return questionService.getQuestion(id);
	}

	@GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestion();
    }
	@GetMapping("/category/{cat}")
	public List<Question> getQuestionsByCategory(@PathVariable("cat") String category){
		return questionService.getQuestionsByCategory(category);
	}
    
    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
    	return questionService.addQuestion(question);
    }
    
    @PostMapping("/addAllQuestion")
    public ResponseEntity<List<Question>> addQuestion(@RequestBody List<Question> questionList) {
    	return questionService.addAllQuestion(questionList);
    }
    
    @PutMapping("/updateQuestion")
    public Question updateQuestion(@RequestBody Question question){
    	return questionService.updateQuestion(question);
    }
    
    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
    	return questionService.deleteQuestion(id);
    }
}
