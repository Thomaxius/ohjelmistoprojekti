package com.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long answerId;
	
    public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@ManyToOne
    @JoinColumn(name = "questionid")
	@JsonBackReference
	private Question question;
    
    private String answer;
    
    public Answer() {}

	public Answer(String answer, Question question) {
		super();
		this.answer = answer;
		this.question = question;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	
	
}
