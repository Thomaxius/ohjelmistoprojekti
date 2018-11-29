package com.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@JsonFilter("questionsFilter")
public class Question {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long questionId;
	private String questionName;  
	private String questionType; //text, radio, checkbox..

	public Question(String questionName, String questionType, Category category) {
		super();
		this.questionName = questionName;
		this.questionType = questionType;
		this.category = category;
	}

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @JsonBackReference
	private Category category;	
	
	//User user (addedby)
    //Category questionCategory

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	 // Blokkaa äärettömän loopin, jonka onetomany aiheuttaa
	private List<Answer> answers;
	
	public Question() {
		super();
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

	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
