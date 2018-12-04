package com.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonView(Views.Internal.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long answerId;
    private Long questionId;
	

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@ManyToOne
    @JoinColumn(name = "question")
	@JsonBackReference

	private Question question;

    private String answer;
    
    public Answer() {}

	public Answer(String answer, Question question) {
		super();
		this.answer = answer;
		this.question = question;
	}

	public Answer(String answer, Long questionId) {
		super();
		this.answer = answer;
		this.questionId = questionId;
	}	
	
    @JsonView(Views.Internal.class)
	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	
	
}
