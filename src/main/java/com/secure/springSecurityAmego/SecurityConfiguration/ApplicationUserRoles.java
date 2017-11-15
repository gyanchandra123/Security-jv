package com.secure.springSecurityAmego.SecurityConfiguration;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;
 

public enum ApplicationUserRoles {

	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(AplicationUserPermissions.STUDENT_READ,
			              AplicationUserPermissions.STUDENT_WRITE,
			              AplicationUserPermissions.COURSE_READ,
			              AplicationUserPermissions.COURSE_WRITE)),
	
	ADMINTRAINEE(Sets.newHashSet(AplicationUserPermissions.STUDENT_WRITE,
                                AplicationUserPermissions.STUDENT_WRITE)
            
            );

	private final Set<AplicationUserPermissions> permissions;

	public Set<AplicationUserPermissions> getPermissions() {
		return permissions;
	}


	ApplicationUserRoles(Set<AplicationUserPermissions> permission) {
		this.permissions = permission;
	}

	
	//adding all the permission to a role through SimpleGrantedAuthority
	// it is returning roles with their associated permissions of types SimpleGrantedAuthority
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissions =getPermissions().stream().map(permission -> 
		
		         new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
		
		return permissions;
		
	}
	
}
