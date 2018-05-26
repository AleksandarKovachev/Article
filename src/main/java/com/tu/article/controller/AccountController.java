package com.tu.article.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.User;
import com.tu.article.service.UserService;

/**
 * Controller for working with the user account
 * 
 * @author aleksandar.kovachev
 *
 */
@Controller
public class AccountController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{" + RequestAttribute.USERNAME + "}", method = RequestMethod.GET)
	public ModelAndView account(@PathVariable(value = RequestAttribute.USERNAME) String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		User user = userService.getActiveUserByUsername(username);
		if (user == null) {
			String message = messageSource.getMessage("account.missing.error", null, Locale.getDefault());
			modelMap.addAttribute(RequestAttribute.MESSAGE, String.format(message, username));
		} else {
			modelMap.addAttribute(RequestAttribute.USER, user);
		}
		return new ModelAndView(ViewConstant.ACCOUNT, modelMap);
	}

}
