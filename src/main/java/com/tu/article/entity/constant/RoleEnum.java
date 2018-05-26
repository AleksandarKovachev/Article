package com.tu.article.entity.constant;

/**
 * Enumeration for defining the database roles
 * 
 * @author aleksandar.kovachev
 *
 */
public enum RoleEnum {

	ROLE_ADMINISTRATOR(1),
	ROLE_REVIEWER(2),
	ROLE_AUTHOR(3),
	ROLE_USER(4);

	private long roleId;

	private RoleEnum(int roleId) {
		this.roleId = roleId;
	}

	public long getRoleId() {
		return roleId;
	}

}
