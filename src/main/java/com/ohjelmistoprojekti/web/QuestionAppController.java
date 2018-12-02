package com.ohjelmistoprojekti.web;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ohjelmistoprojekti.domain.Answer;
import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.Category;
import com.ohjelmistoprojekti.domain.CategoryRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;


@Controller
@RestController
public class QuestionAppController {
 
	@Autowired
	private QuestionRepository questionRepository; 

	@Autowired
	private AnswerRepository answerRepository; 

	@Autowired
	private CategoryRepository categoryRepository; 

	@RequestMapping(value="/categories", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Category> categoriesListRest() {	
        return (List<Category>) categoryRepository.findAll();
    }   	
	
	@RequestMapping(path = "/questions")
    public @ResponseBody List<Question> questionListRest() {	
        return (List<Question>) questionRepository.findAll();
    }    
	
	// RESTful service to find question by id
    @RequestMapping(value="/question/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Question> findStudentRest(@PathVariable("id") Long id) {	
    	return questionRepository.findById(id);
    }      

	
	@RequestMapping(value="/answers", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Answer> answerListRest() {	
        return (List<Answer>) answerRepository.findAll();
    }    

	// RESTful service to find answer by id
    @RequestMapping(value="/answer/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Answer> findAnswerRest(@PathVariable("id") Long id) {	
    	return answerRepository.findById(id);
    }      
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletequestion(@PathVariable("id") Long questionId, Model model) {
    	questionRepository.deleteById(questionId);
        return "redirect:../questionlist";
    }    
    
	@RequestMapping(value="/saveanswer", method = { RequestMethod.POST })
    public String answerSavePost(@RequestBody Answer answer) {
		answer.setQuestion(questionRepository.findById(answer.getQuestionId()).get());
		answerRepository.save(answer);
        return "answer POST succesful. Maybe.";
    }  	    
    
    @RequestMapping(value = "/savequestion", method = RequestMethod.POST)
    public String save(Question question){
        questionRepository.save(question);
        return "Question POST succesful. Maybe.";
    }    
    
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String saveCategory(Category category){
        categoryRepository.save(category);
        return "Category POST succesful. Maybe.";
    }    

} 