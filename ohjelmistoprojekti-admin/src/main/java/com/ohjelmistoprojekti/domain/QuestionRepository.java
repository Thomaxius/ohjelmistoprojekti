package com.ohjelmistoprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByQuestionName(String questionName);
    
}