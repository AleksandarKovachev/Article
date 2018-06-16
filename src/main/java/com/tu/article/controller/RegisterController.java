package com.tu.article.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.constant.Constant;
import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.Degree;
import com.tu.article.entity.Role;
import com.tu.article.entity.User;
import com.tu.article.entity.constant.RoleEnum;
import com.tu.article.form.UserForm;
import com.tu.article.service.DatabaseManagerService;
import com.tu.article.service.DegreeService;

/**
 * Login controller for defining the login logic
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class RegisterController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DegreeService degreeService;

	@Autowired
	private DatabaseManagerService databaseManager;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registerView(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) UserForm userForm) {
		ModelMap modelMap = new ModelMap();
		userForm.setDegree(Constant.INVALID_SELECTION);
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

		List<String> messages = userForm.validate();
		if (CollectionUtils.isEmpty(messages)) {
			modelMap.addAttribute(RequestAttribute.ERRORS, messages);
		} else {
			User user = new User();
			user.setUsername(userForm.getUsername());
			user.setPassword(passwordEncoder.encode(userForm.getPassword()));
			user.setEmail(userForm.getEmail());
			user.setName(userForm.getName());
			user.setOrganization(userForm.getOrganization());
			user.setCreateDate(new Date());
			user.setStatus(databaseManager.getActiveStatus());
			user.setIsConfirmed(0);
			user.setDegree((Degree) databaseManager.getObjectById(Degree.class, Long.valueOf(userForm.getDegree())));
			Role role = (Role) databaseManager.getObjectById(Role.class, RoleEnum.ROLE_USER.getRoleId());
			user.setRole(role);
			databaseManager.addObject(user);

			modelMap.addAttribute(RequestAttribute.MESSAGE, "Успешна регистрация.");
		}

		return new ModelAndView(ViewConstant.REGISTER, modelMap);
	}

}
