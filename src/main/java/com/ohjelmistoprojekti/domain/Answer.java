package com.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long answerId;
	private String answerText;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "questionid")
	private Question question;
	
    public Answer() {}

	public Answer(String answerText, Question question) {
		super();
		this.answerText = answerText;
		this.question = question;
	}

    
	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Answer(Long answerId, String answerText) {
		super();
		this.answerId = answerId;
		this.answerText = answerText;
	}
	
	
}
