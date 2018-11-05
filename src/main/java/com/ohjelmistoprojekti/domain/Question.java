package com.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
	private String questionName;

	//User user (addedby)
    //Category questionCategory
	
	public Question(String questionname) {
		super();
		this.questionName = questionname;
	}
	
	public Question() {
		super();
	}
	
	public long getId() {
		return questionId;
	}
	public void setId(long questionid) {
		this.questionId = questionid;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionname) {
		this.questionName = questionname;
	}

	
}
