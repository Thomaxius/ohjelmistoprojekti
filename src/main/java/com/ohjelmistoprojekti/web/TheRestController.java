package com.ohjelmistoprojekti.web;



import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohjelmistoprojekti.QuestionApplication;
import com.ohjelmistoprojekti.domain.Answer;
import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.Category;
import com.ohjelmistoprojekti.domain.CategoryRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;
import com.ohjelmistoprojekti.domain.Views;


@Controller
@RestController
public class TheRestController {
	private static final Logger log = LoggerFactory.getLogger(TheRestController.class);
	@Autowired
	private QuestionRepository questionRepository; 

	@Autowired
	private AnswerRepository answerRepository; 

	@Autowired
	private CategoryRepository categoryRepository; 

	@RequestMapping(value="/categories", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Category> categoriesListRest() throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    String result = mapper
	      .writerWithView(Views.Public.class)
	      .writeValueAsString((List<Category>) categoryRepository.findAll());
	    List<Category> JsonList = mapper.readValue(result, new TypeReference<List<Category>>(){});
        return JsonList;
    }   	
	
	@RequestMapping(path = "/questions")
    @CrossOrigin
    public @ResponseBody List<Question> questionListRest() throws IOException {	
	    ObjectMapper mapper = new ObjectMapper();
	    String result = mapper
	      .writerWithView(Views.Public.class)
	      .writeValueAsString((List<Question>) questionRepository.findAll());  
	    List<Question> JsonList = mapper.readValue(result, new TypeReference<List<Question>>(){});
        return JsonList;
    }     
	
	@RequestMapping(path = "/fullapi")
    @CrossOrigin
    public @ResponseBody List<Category> fullApi() throws IOException {	
	    ObjectMapper mapper = new ObjectMapper();
	    String result = mapper
	      .writerWithView(Views.Internal.class)
	      .writeValueAsString((List<Category>) categoryRepository.findAll());
	    List<Category> JsonList = mapper.readValue(result, new TypeReference<List<Category>>(){});
        return JsonList;
    }   	
	// RESTful service to find question by id
    @RequestMapping(value="/question/{id}", method = RequestMethod.GET)
    @CrossOrigin
    public @ResponseBody Optional<Question> findStudentRest(@PathVariable("id") Long id) {	
    	return questionRepository.findById(id);
    }      

	
	@RequestMapping(value="/answers", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Answer> answerListRest() throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    String result = mapper
	      .writerWithView(Views.Public.class)
	      .writeValueAsString((List<Answer>) answerRepository.findAll());
	    List<Answer> JsonList = mapper.readValue(result, new TypeReference<List<Answer>>(){});
        return JsonList;
    }    

	// RESTful service to find answer by id
    @RequestMapping(value="/answer/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Answer> findAnswerRest(@PathVariable("id") Long id) {	
    	return answerRepository.findById(id);
    }      
    
	@RequestMapping(value="/savesingleanswer", method = { RequestMethod.POST })
    @CrossOrigin
	public ResponseEntity<Object> answerSavePost(@RequestBody Answer answer) {
	    try {
			answer.setQuestion(questionRepository.findById(answer.getQuestionId()).get());
			answerRepository.save(answer);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
    	catch(Exception ex) {
        	System.out.println("There was an error saving a single answer: " + ex);
        	if (ex.getMessage().contains("No value present")) {
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: no value present. A questionId you are refering to does not exist.");        		
        	}
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    	}  	    
	}
    
	@RequestMapping(value="/saveanswers", method = { RequestMethod.POST })
    @CrossOrigin
    public ResponseEntity<Object> answerSavePost(@RequestBody List<Answer> list) {
	    try {
			for (Answer answer : list) {	
				answer.setQuestion(questionRepository.findById(answer.getQuestionId()).get());
				answerRepository.save(answer);
			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
        }	catch(Exception ex) {
        	System.out.println("There was an error saving answers: " + ex.getMessage());
        	if (ex.getMessage().contains("No value present")) {
    			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: no value present. A questionId you are refering to does not exist.");        		
        	}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }	    
    }  	 
	
    @RequestMapping(value = "/savequestion", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> save(Question question){
	    try {
	    	questionRepository.save(question);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
    	catch(Exception ex) {
    		System.out.println("There was an error saving a question: " + ex);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    	}
    }    
    
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    @CrossOrigin
    public ResponseEntity<Object> saveCategory(Category category){
	    try {
	    	categoryRepository.save(category);
			return ResponseEntity.status(HttpStatus.CREATED).build();
	    }
    	catch(Exception ex) {
    		System.out.println("There was an error saving a category: " + ex);
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    	}
        
    }    

} 