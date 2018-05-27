package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.ViewConstant;

/**
 * Controller for defining the functionalities to work with the users
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class UserController {

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView account(
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ViewConstant.USERS, modelMap);
	}

}
