package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.entity.User;
import com.tu.article.service.UserService;

/**
 * Home controller for working with the /index context
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		User user = userService.getActiveUserByUsername("drage503");
		return new ModelAndView("index", modelMap);
	}

}
