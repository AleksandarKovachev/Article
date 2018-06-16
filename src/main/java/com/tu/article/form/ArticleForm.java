package com.tu.article.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tu.article.constant.Constant;

import lombok.Data;

/**
 * Form for adding new article
 *
 * @author aleksandar.kovachev
 *
 */
@Data
public class ArticleForm {

	private String title;

	private String abstractText;

	private List<Long> authors;

	private Long categoryId;

	private List<String> keywords;

	private MultipartFile articleFile;

	public List<String> validate() {
		List<String> messages = new ArrayList<>();

		if (!StringUtils.hasText(title)) {
			messages.add("Заглавие е задължително поле");
		}

		if (!StringUtils.hasText(abstractText)) {
			messages.add("Анотация е задължително поле");
		}

		if (CollectionUtils.isEmpty(authors)) {
			messages.add("Задължително е да се избере поне един автор");
		}

		if (categoryId == null || categoryId.equals(Constant.INVALID_SELECTION)) {
			messages.add("Категория е задължително поле");
		}

		if (CollectionUtils.isEmpty(authors)) {
			messages.add("Задължително е да се добави поне една ключова дума");
		}

		if (articleFile == null) {
			messages.add("Задължително е да прикачи файл със статията");
		}

		String extension = FilenameUtils.getExtension(articleFile.getOriginalFilename());
		if (!Arrays.asList(Constant.TEXT_FILE_EXTENSIONS).contains(extension)) {
			messages.add("Прикаченият файл не е в текстов формат");
		}

		return messages;
	}

}
