package com.tu.article.form;

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

}
