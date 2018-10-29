package com.ohjelmistoprojekti.domain;

public class Question {

	private long questionid;
	private String questionname;
	//Category questionCategory
	//User user (addedby)
	
	public Question(long questionid, String questionname) {
		super();
		this.questionid = questionid;
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
