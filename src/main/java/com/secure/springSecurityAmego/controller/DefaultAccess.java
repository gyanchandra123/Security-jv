package com.secure.springSecurityAmego.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DefaultAccess {
	
	@GetMapping(value= "/")
	public String greeting() {
		return "Hello , welcome to the spring security class DEFAULT";
	}

}
