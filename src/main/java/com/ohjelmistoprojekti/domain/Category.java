package com.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonBackReference
	private List<Question> questions;
	
	public Category() {}
	
	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	
	
}
