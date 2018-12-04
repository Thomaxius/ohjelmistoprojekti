package com.ohjelmistoprojekti.web;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ohjelmistoprojekti.domain.AnswerRepository;
import com.ohjelmistoprojekti.domain.CategoryRepository;
import com.ohjelmistoprojekti.domain.Question;
import com.ohjelmistoprojekti.domain.QuestionRepository;

@Controller
public class AdminController {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository; 

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indeksi() {
		return "index";
	}

	// Login
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	
	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/questionlist")
	public String questionList(Model model) {
    	model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("questions", questionRepository.findAll());
		return "questionlist";
	}
	
	
	@Transactional
    @RequestMapping(value = "/deletequestion/{id}", method = RequestMethod.GET)
    public String deleteQuestion(@PathVariable("id") Long questionId, Model model) {
    	questionRepository.deleteById(questionId);
        return "redirect:../questionlist";
    }   
	
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/deleteanswer/{id}", method = RequestMethod.GET)
    public String deleteAnswer(@PathVariable("id") Long answerId, Model model) {
    	answerRepository.deleteById(answerId);
        return "redirect:../questionlist";
    } 	
	@RequestMapping(value = "/editquestion/{id}")
	public String editquestion(@PathVariable("id") Long questionId, Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("question", questionRepository.findById(questionId));
		return "editquestion";
	}
	
	@RequestMapping(value = "/addquestion")
	public String addquestion(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("question", new Question());
		return "addquestion";
	}
	
	@RequestMapping(value = "/savequestion2", method = RequestMethod.POST)
	public String save2(Question question) {
		question.setCategory(categoryRepository.findByCategoryName(question.getCategory().getCategoryName()).get(0));
		questionRepository.save(question);
		return "redirect:questionlist";
	}
	
	
	
}
