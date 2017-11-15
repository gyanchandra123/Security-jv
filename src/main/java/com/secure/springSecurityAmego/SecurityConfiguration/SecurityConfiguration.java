package com.secure.springSecurityAmego.SecurityConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private final PasswordEncoder passwordEncoder;
	
	
	public SecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder= passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * this will provide default access to greeting() method API but provide
		 * authorization to the other end points with the default username and the password 
		 * provided at the time of running the program.
		 * 
		 */
		http 
			.csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/") .permitAll()
		    .antMatchers("/api/**").hasRole(ApplicationUserRoles.STUDENT.name())
		    
		    .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAnyAuthority(AplicationUserPermissions.COURSE_WRITE.getPermission())
		    .antMatchers(HttpMethod.POST, "/management/api/**").hasAnyAuthority(AplicationUserPermissions.COURSE_WRITE.getPermission())
		    .antMatchers(HttpMethod.PUT, "/management/api/**").hasAnyAuthority(AplicationUserPermissions.COURSE_WRITE.getPermission())
		    .antMatchers(HttpMethod.GET, "/management/api/v1/students").hasAnyRole(ApplicationUserRoles.ADMIN.name(),ApplicationUserRoles.ADMINTRAINEE.name())
		 
		    .anyRequest()
		    .authenticated()
		    .and()
		    .httpBasic();  // means we are using basic authentication

		/*
		 * now we will provide our own username and password to access any authenticated  
		 * end points
		 */

	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {  

		UserDetails userCredential= User.builder()
		 				.username("gyan")
		 				.password(passwordEncoder.encode("pwd"))
		 				//.roles(ApplicationUserRoles.STUDENT.name())
		 				.authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities())		 				 
		 				.build();
		
		UserDetails  adminCredential = User.builder()
						.username("admin") // linda
						.password(passwordEncoder.encode("admin"))
						//.roles(ApplicationUserRoles.ADMIN.name())
						.authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities())
						 
						.build();
		
		
		UserDetails  adminTraineeCredential = User.builder()
				        .username("trainee") //tom
				        .password(passwordEncoder.encode("trainee"))
				        //.roles(ApplicationUserRoles.ADMINTRAINEE.name())
				        .authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthorities())
				        .build();
		
		return new InMemoryUserDetailsManager(userCredential,adminCredential,adminTraineeCredential);
	}
}
