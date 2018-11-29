package com.ohjelmistoprojekti.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long answerId;
	
    @ManyToOne
    @JoinColumn(name = "questionid")
	private Question question;
    
    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> answers = new ArrayList<String>();
    
    public Answer() {}

	public Answer(String answerText, Question question) {
		super();
		answers.add(answerText);
		this.question = question;
	}

	public Answer(List<String> answers, Question question) {
		super();
		this.answers = answers;
		this.question = question;
	}
    
	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public List<String> getAnswers() {
		return answers;
	}
	
	
}
