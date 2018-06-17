package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;

/**
 * Controller for working with reviews
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class ReviewController {

	@RequestMapping(value = "/review/{" + RequestAttribute.USERNAME + "}", method = RequestMethod.GET)
	public ModelAndView account(@PathVariable(value = RequestAttribute.USERNAME) String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ViewConstant.ACCOUNT_REVIEWS, modelMap);
	}

}
