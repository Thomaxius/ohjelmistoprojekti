package com.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
	
	public long getQuestionid() {
		return questionId;
	}
	public void setQuestionid(long questionid) {
		this.questionId = questionid;
	}
	public String getQuestionname() {
		return questionName;
	}
	public void setQuestionname(String questionname) {
		this.questionName = questionname;
	}

	
}
