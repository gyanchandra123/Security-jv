package com.secure.springSecurityAmego.SecurityConfiguration;

public enum AplicationUserPermissions {

	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permission;

	public String getPermission() {
		return permission;
	}

	private AplicationUserPermissions(String permission) {
		this.permission = permission;
	}
	
	
	
}
