package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.form.UserForm;
import com.tu.article.service.DegreeService;

/**
 * Login controller for defining the login logic
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class RegisterController {

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Autowired
	private DegreeService degreeService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerView(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) UserForm userForm) {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute(RequestAttribute.FORM, userForm);
		modelMap.addAttribute(RequestAttribute.DEGREES, degreeService.getAllDegrees());
		return new ModelAndView(ViewConstant.REGISTER, modelMap);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerAction(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) UserForm userForm) {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute(RequestAttribute.FORM, userForm);
		modelMap.addAttribute(RequestAttribute.DEGREES, degreeService.getAllDegrees());
		return new ModelAndView(ViewConstant.REGISTER, modelMap);
	}

}
