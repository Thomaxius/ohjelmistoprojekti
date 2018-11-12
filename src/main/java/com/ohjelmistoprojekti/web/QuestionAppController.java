package com.ohjelmistoprojekti.web;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;


@Controller
public class QuestionAppController {
	
	@Autowired
	private QuestionRepository repository;
	
	// Index page
    @RequestMapping(value="/index")
    public String index() {	
        return "index";
    }
    
    // REST
    @RequestMapping(value="/questions", method = RequestMethod.GET)
	@CrossOrigin
    public @ResponseBody List<Question> questionListRest() {	
      return (List<Question>) repository.findAll();
  }
}
