package com.tu.article.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tu.article.constant.Constant;

import lombok.Data;

/**
 * Form for registering new user
 *
 * @author aleksandar.kovachev
 *
 */
@Data
public class UserForm {

	private String username;

	private String password;

	private String email;

	private String name;

	private String organization;

	private Long degree;

	public List<String> validate() {
		List<String> messages = new ArrayList<>();
		if (!StringUtils.hasText(username)) {
			messages.add("Потребителското име е задължително поле.");
		}
		if (!StringUtils.hasText(password)) {
			messages.add("Паролата е задължително поле.");
		}
		if (!StringUtils.hasText(email)) {
			messages.add("Имейла е задължително поле.");
		}
		if (!StringUtils.hasText(name)) {
			messages.add("Името е задължително поле.");
		}
		if (!StringUtils.hasText(organization)) {
			messages.add("Организация е задължително поле.");
		}
		if (degree == Constant.INVALID_SELECTION) {
			messages.add("Не сте избрали степен на образование.");
		}

		return messages;
	}

}
