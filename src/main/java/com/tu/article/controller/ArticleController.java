package com.tu.article.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tu.article.controller.constant.RequestAttribute;
import com.tu.article.controller.constant.ViewConstant;
import com.tu.article.entity.Article;
import com.tu.article.entity.ArticleFile;
import com.tu.article.entity.Keyword;
import com.tu.article.entity.Parameter;
import com.tu.article.entity.User;
import com.tu.article.entity.constant.SystemParameter;
import com.tu.article.form.ArticleForm;
import com.tu.article.security.UserDetailsImpl;
import com.tu.article.service.ArticleCategoryService;
import com.tu.article.service.DatabaseManagerService;
import com.tu.article.service.KeywordService;
import com.tu.article.service.ParameterService;
import com.tu.article.service.UserService;

/**
 * Controller for working with articles
 *
 * @author aleksandar.kovachev
 *
 */
@Controller
public class ArticleController {

	@Autowired
	private ArticleCategoryService articleCategoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private KeywordService keywordService;

	@Autowired
	private DatabaseManagerService databaseManagerService;

	@Autowired
	private ParameterService parameterService;

	@RequestMapping(value = "/article/{" + RequestAttribute.USERNAME + "}", method = RequestMethod.GET)
	public ModelAndView account(@PathVariable(value = RequestAttribute.USERNAME) String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView("index", modelMap);
	}

	@RequestMapping(value = "/article/add", method = RequestMethod.GET)
	public ModelAndView addArticle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) ArticleForm form) {
		ModelMap modelMap = new ModelMap();
		form.setCategoryId(-1L);
		List<Long> authors = new ArrayList<>();
		authors.add(-1L);
		form.setAuthors(authors);
		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
		modelMap.addAttribute(RequestAttribute.AUTHORS, userService.getAuthors());
		return new ModelAndView(ViewConstant.ADD_ARTICLE, modelMap);
	}

	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public ModelAndView saveArticle(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(RequestAttribute.FORM) ArticleForm form) throws IOException {
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
					+ form.getArticleFile().getOriginalFilename();

			uploadFile(form.getArticleFile(), fileName);

			ArticleFile articleFile = new ArticleFile();
			articleFile.setName(fileName);
			articleFile.setStatus(databaseManagerService.getActiveStatus());
			Long articleFileId = databaseManagerService.addObject(articleFile);

			Article article = new Article();
			article.setTitle(form.getTitle());
			article.setAbstractColumn(form.getAbstractText());
			article.setArticleCategoryId(form.getCategoryId());
			article.setCreateDate(new Date());
			article.setUserId(userDetails.getId());
			article.setArticleFileId(articleFileId);

			Set<Keyword> keywords = new HashSet<>();
			for (String name : form.getKeywords()) {
				Keyword keyword = keywordService.getKeywordByName(name);
				if (keyword == null) {
					keyword = new Keyword();
					keyword.setName(name);
					databaseManagerService.addObject(keyword);
				}
				keywords.add(keyword);
			}
			article.setKeywords(keywords);

			Set<User> authors = new HashSet<>();
			for (Long id : form.getAuthors()) {
				User user = (User) databaseManagerService.getObjectById(User.class, id);
				authors.add(user);
			}
			article.setAuthors(authors);

			databaseManagerService.addObject(article);
		}

		modelMap.addAttribute(RequestAttribute.FORM, form);
		modelMap.addAttribute(RequestAttribute.ARTICLE_CATEGORIES, articleCategoryService.getAllArticleCategories());
		modelMap.addAttribute(RequestAttribute.AUTHORS, userService.getAuthors());
		return new ModelAndView(ViewConstant.ADD_ARTICLE, modelMap);
	}

	@RequestMapping(value = "/keywords", method = RequestMethod.GET)
	public @ResponseBody List<Keyword> getKeywords(HttpServletRequest request, HttpServletResponse response) {
		return keywordService.getAllKeywords();
	}

	private void uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
		Parameter apacheFilesPath = parameterService.getParameterByName(SystemParameter.APACHE_FILES_PATH.name());
		Parameter apacheArticlesPath = parameterService.getParameterByName(SystemParameter.APACHE_ARTICLES_PATH.name());

		Path path;
		byte[] bytes = multipartFile.getBytes();
		path = Paths.get(apacheFilesPath.getValue() + apacheArticlesPath.getValue() + fileName);
		Files.write(path, bytes);
	}

}
