package com.ohjelmistoprojekti.web;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohjelmistoprojekti.domain.Answer;
import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;


@Controller 
public class QuestionAppController {
 
	@Autowired
	private QuestionRepository questionRepository; 

	@Autowired
	private AnswerRepository answerRepository; 


	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String indeksi() {
		return "index";  
	}
	
	// Login
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	// RESTful service to get all questions
	// @ResponseBody muuttaa JSON:iksi
	@RequestMapping(value="/questions", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Question> questionListRest() {	
        return (List<Question>) questionRepository.findAll();
    }    

	// RESTful service to find question by id
    @RequestMapping(value="/question/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Question> findStudentRest(@PathVariable("id") Long id) {	
    	return questionRepository.findById(id);
    }      
	
	// http://localhost8080/questionlist
    @RequestMapping(value="/questionlist")
    public String questionList(Model model) {	
		model.addAttribute("questions", questionRepository.findAll());
        return "questionlist";
    }

	
	// RESTful service to get all questions
	// @ResponseBody muuttaa JSON:iksi
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
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Question question){
        questionRepository.save(question);
        return "redirect:questionlist";
    }    

    @RequestMapping(value = "/edit/{id}")
	public String editquestion(@PathVariable("id") Long id, Model model){

	model.addAttribute("question", questionRepository.findById(id));
	return "editquestion";
	}
} 