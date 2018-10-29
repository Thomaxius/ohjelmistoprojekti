package com.ohjelmistoprojekti.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long questionid;
	private String questionname;
	//Category questionCategory
	//User user (addedby)
	
	public Question(String questionname) {
		super();
		this.questionname = questionname;
	}
	public Question() {
		super();
	}
	
	public long getQuestionid() {
		return questionid;
	}
	public void setQuestionid(long questionid) {
		this.questionid = questionid;
	}
	public String getQuestionname() {
		return questionname;
	}
	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}

	
}
