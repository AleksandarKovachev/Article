package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.ViewConstant;

/**
 * Login controller for defining the login logic
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class LoginController {

	@GetMapping
	@RequestMapping(value = "/login")
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ViewConstant.LOGIN, modelMap);
	}

}
