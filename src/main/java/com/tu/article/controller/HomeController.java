package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Home controller for working with the /index context
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("index", modelMap);
	}

}
