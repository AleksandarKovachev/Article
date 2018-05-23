package com.tu.article.form;

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

	private int degree;

	public String validate() {
		StringBuilder messages = new StringBuilder();
		if (!StringUtils.hasText(username)) {
			messages.append("Потребителското име е задължително поле. ");
		}
		if (!StringUtils.hasText(password)) {
			messages.append("Паролата е задължително поле. ");
		}
		if (!StringUtils.hasText(email)) {
			messages.append("Имейла е задължително поле. ");
		}
		if (!StringUtils.hasText(name)) {
			messages.append("Името е задължително поле. ");
		}
		if (!StringUtils.hasText(organization)) {
			messages.append("Организация е задължително поле. ");
		}
		if (degree == Constant.INVALID_SELECTION) {
			messages.append("Не сте избрали степен на образование. ");
		}

		return messages.toString().trim();
	}

}
