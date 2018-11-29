package com.ohjelmistoprojekti.web;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ohjelmistoprojekti.domain.Answer;
import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.CategoryRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;


@Controller 
public class QuestionAppController {
 
	@Autowired
	private QuestionRepository questionRepository; 

	@Autowired
	private AnswerRepository answerRepository; 

	@Autowired
	private CategoryRepository categoryRepository; 
	
	FilterProvider filters = new SimpleFilterProvider()
			.setFailOnUnknownId(false);
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indeksi() {
		return "index";  
	}
	
	// Login
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);

        return mapper;
    }

	@RequestMapping(value="/questions", method = RequestMethod.GET)
	@CrossOrigin
	@RestResource
    public @ResponseBody String questionListRest() throws JsonProcessingException {
		System.out.println("lolololo");
	    ObjectMapper mapper = new ObjectMapper();
	    SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
	    .serializeAllExcept("answers", "category");
	    FilterProvider filters = new SimpleFilterProvider()
	    .addFilter("questionsFilter", theFilter);
	    String dtoAsString = mapper.writer(filters).writeValueAsString(questionRepository.findAll());
	    return dtoAsString;
    }    


	@RequestMapping(value="/categories", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody String categoriesListRest() throws JsonProcessingException {
	    ObjectMapper mapper = new ObjectMapper();
	    SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter
	    		
	    .serializeAllExcept("questions");
		FilterProvider filters = new SimpleFilterProvider()
				.setFailOnUnknownId(false) // Muuten räjähtää, koska entity viittaa toiseen entitytyn jolla on filtteri..
	    .addFilter("categoryFilter", theFilter);
	    String dtoAsString = mapper.writer(filters).writeValueAsString(categoryRepository.findAll());
	    return dtoAsString;
    }   	

	@RequestMapping(value="/questionsfull", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody String fullQuestionsRest() throws JsonProcessingException {
	    ObjectMapper mapper = new ObjectMapper();
	    SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider()
				.setFailOnUnknownId(false); // Muuten räjähtää, koska entity viittaa toiseen entitytyn jolla on filtteri..
	    String dtoAsString = mapper.writer(filters).writeValueAsString(questionRepository.findAll());
	    return dtoAsString;
    }   	

	@RequestMapping(value="/categoriesfull", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody String fullCategoriesRest() throws JsonProcessingException {
	    ObjectMapper mapper = new ObjectMapper();
	    SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider()
				.setFailOnUnknownId(false); // Muuten räjähtää, koska entity viittaa toiseen entitytyn jolla on filtteri..
	    String dtoAsString = mapper.writer(filters).writeValueAsString(categoryRepository.findAll());
	    return dtoAsString;
    }   
	
	// RESTful service to find question by id
    @RequestMapping(value="/question/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Question> findStudentRest(@PathVariable("id") Long id) {
    	System.out.println("kek");
    	return questionRepository.findById(id);
    }      
	
	// http://localhost8080/questionlist
    @RequestMapping(value="/questionlist")
    public String questionList(Model model) {	
		model.addAttribute("questions", questionRepository.findAll());
        return "questionlist";
    }

	

	@RequestMapping(value="/answers", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Answer> answerListRest() {	
        return (List<Answer>) answerRepository.findAll();
    }    

	// RESTful service to find question by id
    @RequestMapping(value="/answer/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Answer> findAnswerRest(@PathVariable("id") Long id) {	
    	return answerRepository.findById(id);
    }      
	
	// http://localhost8080/questionlist
    @RequestMapping(value="/answerlist")
    public String answerList(Model model) {	
		model.addAttribute("answers", answerRepository.findAll());
        return "answerlist";
    }    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletequestion(@PathVariable("id") Long questionId, Model model) {
    	questionRepository.deleteById(questionId);
        return "redirect:../questionlist";
    }   
    
    // http://localhost8080/addquestion
    @RequestMapping(value = "/add")
    public String addquestion(Model model){

    	model.addAttribute("question", new Question());

        return "addquestion";
    }     
      
    
    @RequestMapping(value = "/savequestion", method = RequestMethod.POST)
    public String save(Question question){
        questionRepository.save(question);
        return "redirect:questions";
    }    
    
    @RequestMapping(value = "/saveanswer", method = RequestMethod.POST)
    public String save(Answer answer){
        answerRepository.save(answer);
        return "redirect:answers";
    }
    
    @RequestMapping(value = "/edit/{id}")
	public String editquestion(@PathVariable("id") Long id, Model model){

	model.addAttribute("question", questionRepository.findById(id));
	return "editquestion";
	}
} 