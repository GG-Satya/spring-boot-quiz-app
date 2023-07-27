package com.example.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	@ManyToMany
	private List<Question> questionList;
	
	public Quiz() {
		super();
	}
	public Quiz(long id, String title, List<Question> questionList) {
		super();
		this.id = id;
		this.title = title;
		this.questionList = questionList;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	@Override
	public String toString() {
		return "Quiz [id=" + id + ", title=" + title + ", questionList=" + questionList + "]";
	}
}
