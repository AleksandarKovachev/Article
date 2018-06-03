package com.tu.article.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.filter.BasePageFilter;
import com.tu.article.service.DatabaseManagerService;
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

	@Autowired
	private DatabaseManagerService databaseManager;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView users(HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		BasePageFilter filter = new BasePageFilter(10);
		modelMap.addAttribute(RequestAttribute.FILTER, filter);
		modelMap.addAttribute(RequestAttribute.USERS, userService.getUsers(filter));
		modelMap.addAttribute(RequestAttribute.STATUSES, databaseManager.getAllStatuses());
		modelMap.addAttribute(RequestAttribute.ROLES, databaseManager.getAllRoles());
		return new ModelAndView(ViewConstant.USERS, modelMap);
	}

	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
	public @ResponseBody Boolean updateUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id, @RequestParam("roleId") String role,
			@RequestParam("statusId") String status) {
		Long userId, roleId, statusId;
		try {
			userId = Long.parseLong(id);
			roleId = Long.parseLong(role);
			statusId = Long.parseLong(status);
		} catch (NumberFormatException e) {
			return false;
		}
		return userService.updateUserRoleAndStatus(userId, roleId, statusId);
	}

}
