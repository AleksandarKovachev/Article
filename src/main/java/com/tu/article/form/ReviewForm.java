package com.tu.article.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
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
public class ReviewForm {

	public Long articleStatusId;

	private MultipartFile reviewFile;

	public List<String> validate() {
		List<String> messages = new ArrayList<>();

		if (articleStatusId == null || articleStatusId.equals(Constant.INVALID_SELECTION)) {
			messages.add("Не е избран статус за статията");
		}

		if (reviewFile == null || reviewFile.isEmpty()) {
			messages.add("Задължително е да прикачи файл с рецензията");
		} else {
			String extension = FilenameUtils.getExtension(reviewFile.getOriginalFilename());
			if (!Arrays.asList(Constant.TEXT_FILE_EXTENSIONS).contains(extension)) {
				messages.add("Прикаченият файл не е в текстов формат");
			}
		}

		return messages;
	}

}
