package com.ohjelmistoprojekti.web;

import org.springframework.web.bind.annotation.RequestMapping;

public class Controller {
	
	// Index page
    @RequestMapping(value="/index")
    public String index() {	
        return "index";
    }
}
