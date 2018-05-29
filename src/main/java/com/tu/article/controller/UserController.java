package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.filter.BasePageFilter;
import com.tu.article.service.UserService;

/**
 * Controller for defining the functionalities to work with the users
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		BasePageFilter filter = new BasePageFilter(10);
		modelMap.addAttribute(RequestAttribute.FILTER, filter);
		modelMap.addAttribute(RequestAttribute.USERS, userService.getUsers(filter));
		return new ModelAndView(ViewConstant.USERS, modelMap);
	}

}
