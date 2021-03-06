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
import com.tu.article.entity.Image;
import com.tu.article.entity.Parameter;
import com.tu.article.entity.User;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.service.ImageService;
import com.tu.article.service.ParameterService;
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

	@Autowired
	private ImageService imageService;

	@Autowired
	private ParameterService parameterService;

	@RequestMapping(value = "/{" + RequestAttribute.USERNAME + "}", method = RequestMethod.GET)
	public ModelAndView account(@PathVariable(value = RequestAttribute.USERNAME) String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute(RequestAttribute.USERNAME, username);
		User user = userService.getActiveUserByUsername(username);
		if (user == null) {
			String message = messageSource.getMessage("account.missing.error", null, Locale.getDefault());
			modelMap.addAttribute(RequestAttribute.MESSAGE, String.format(message, username));
		} else {
			Image image = imageService.getActiveImageByUser(user.getId());
			Parameter apacheServerAddress = parameterService
					.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name());
			Parameter apacheImagePath = parameterService.getParameterByName(SystemParameter.APACHE_IMAGES_PATH.name());
			if (image != null) {
				modelMap.addAttribute(RequestAttribute.IMAGE,
						apacheServerAddress.getValue() + apacheImagePath.getValue() + image.getName());
			} else {
				modelMap.addAttribute(RequestAttribute.IMAGE,
						apacheServerAddress.getValue() + apacheImagePath.getValue() + "placeholder.png");
			}
			modelMap.addAttribute(RequestAttribute.USER, user);
		}
		return new ModelAndView(ViewConstant.ACCOUNT, modelMap);
	}

}
