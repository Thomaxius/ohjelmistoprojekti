package com.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long questionId;
	private String questionName;  
	private String questionType;



	public Question(String questionName, String questionType) {
		super();
		this.questionName = questionName;
		this.questionType = questionType;
	}

	//User user (addedby)
    //Category questionCategory

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Answer> answers;
	
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
