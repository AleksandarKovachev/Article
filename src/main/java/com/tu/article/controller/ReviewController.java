package com.tu.article.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.constant.Constant;
import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.Article;
import com.tu.article.entity.ArticleStatus;
import com.tu.article.entity.Parameter;
import com.tu.article.entity.Review;
import com.tu.article.entity.ReviewFile;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.form.ReviewForm;
import com.tu.article.security.UserDetailsImpl;
import com.tu.article.service.ArticleService;
import com.tu.article.service.ArticleStatusService;
import com.tu.article.service.DatabaseManagerService;
import com.tu.article.service.ParameterService;
import com.tu.article.service.ReviewService;

/**
 * Controller for working with reviews
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private DatabaseManagerService databaseManagerService;

	@Autowired
	private ArticleStatusService articleStatusService;

	@RequestMapping(value = "/review/{" + RequestAttribute.USERNAME + "}", method = RequestMethod.GET)
	public ModelAndView review(@PathVariable(value = RequestAttribute.USERNAME) String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		Parameter apacheServerAddress = parameterService
				.getParameterByName(SystemParameter.APACHE_SERVER_ADDRESS.name());
		Parameter articlesPath = parameterService.getParameterByName(SystemParameter.APACHE_ARTICLES_PATH.name());
		modelMap.addAttribute(RequestAttribute.URL, apacheServerAddress.getValue() + articlesPath.getValue());
		modelMap.addAttribute(RequestAttribute.ARTICLES, articleService.getArticlesByReviewer(username));
		return new ModelAndView(ViewConstant.ACCOUNT_REVIEWS, modelMap);
	}

	@RequestMapping(value = "/review/add/{id}", method = RequestMethod.GET)
	public ModelAndView addReview(@PathVariable("id") String id, @ModelAttribute(RequestAttribute.FORM) ReviewForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		form.setArticleStatusId(Constant.INVALID_SELECTION);
		modelMap.addAttribute(RequestAttribute.ARTICLE,
				databaseManagerService.getObjectById(Article.class, Long.parseLong(id)));
		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_STATUSES, articleStatusService.getAllArticleStatuses());
		return new ModelAndView(ViewConstant.ADD_REVIEW, modelMap);
	}

	@RequestMapping(value = "/review/add/{id}", method = RequestMethod.POST)
	public ModelAndView addReviewPost(@PathVariable("id") String id,
			@ModelAttribute(RequestAttribute.FORM) ReviewForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ModelMap modelMap = new ModelMap();

		List<String> messages = form.validate();
		if (!CollectionUtils.isEmpty(messages)) {
			modelMap.addAttribute(RequestAttribute.ERRORS, messages);
		} else {
			modelMap.addAttribute(RequestAttribute.MESSAGE, "Успешно добавена статия");

			UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");
			String fileName = userDetails.getUsername() + "_" + simpleDateFormat.format(new Date()) + "_"
					+ form.getReviewFile().getOriginalFilename();

			uploadFile(form.getReviewFile(), fileName);

			ReviewFile reviewFile = new ReviewFile();
			reviewFile.setName(fileName);
			reviewFile.setStatus(databaseManagerService.getActiveStatus());
			reviewFile.setId(databaseManagerService.addObject(reviewFile));

			Review review = new Review();
			review.setCreateDate(new Date());
			review.setArticleStatus((ArticleStatus) databaseManagerService.getObjectById(ArticleStatus.class,
					form.getArticleStatusId()));
			review.setReviewFile(reviewFile);
			review.setStatus(databaseManagerService.getActiveStatus());

			review.setId(databaseManagerService.addObject(review));
			reviewService.updateArticleReviewer(userDetails.getId(), review);
		}

		modelMap.addAttribute(RequestAttribute.ARTICLE,
				databaseManagerService.getObjectById(Article.class, Long.parseLong(id)));
		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_STATUSES, articleStatusService.getAllArticleStatuses());
		return new ModelAndView(ViewConstant.ADD_REVIEW, modelMap);
	}

	private void uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
		Parameter apacheFilesPath = parameterService.getParameterByName(SystemParameter.APACHE_FILES_PATH.name());
		Parameter apacheArticlesPath = parameterService.getParameterByName(SystemParameter.ARTICLE_REVIEWS_PATH.name());

		Path path;
		byte[] bytes = multipartFile.getBytes();
		path = Paths.get(apacheFilesPath.getValue() + apacheArticlesPath.getValue() + fileName);
		Files.write(path, bytes);
	}

}
