package com.example.model;

public class QuizAnsResponse {
	private long id;
	private String enteredAnswer;
	
	public QuizAnsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QuizAnsResponse(long id, String enteredAnswer) {
		super();
		this.id = id;
		this.enteredAnswer = enteredAnswer;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEnteredAnswer() {
		return enteredAnswer;
	}
	public void setEnteredAnswer(String enteredAnswer) {
		this.enteredAnswer = enteredAnswer;
	}
	@Override
	public String toString() {
		return "QuizAnsResponse [id=" + id + ", enteredAnswer=" + enteredAnswer + "]";
	}
	
}
