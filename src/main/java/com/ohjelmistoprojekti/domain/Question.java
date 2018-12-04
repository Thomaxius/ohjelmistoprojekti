package com.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
property  = "questionId", 
scope     = Long.class)
@Entity
public class Question {
	
	@JsonIgnore
	private static AnswerRepository answerRepository; 

	@JsonIgnore
	private static CategoryRepository categoryRepository; 	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long questionId;
	private String questionName;  
	private String questionType; //text, radio, checkbox..
	private String[] values;

	
	public Question(String questionName, String questionType, Category category, String[] values) {
		super();
		this.questionName = questionName;
		this.questionType = questionType;
		this.category = category;
		this.setValues(values);
	}

	public Question(String questionName, String questionType, Category category) {
		super();
		this.questionName = questionName;
		this.questionType = questionType;
		this.category = category;
	}	
	
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "categoryid")
    @JsonView(Views.Internal.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Category category;	

    @JsonView(Views.Internal.class)
    public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
    
    @JsonView(Views.Internal.class)
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "question")
    @JsonManagedReference
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
	
    @JsonView(Views.Internal.class)
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

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}



}
