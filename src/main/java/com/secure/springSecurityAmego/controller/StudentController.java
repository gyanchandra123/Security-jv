package com.secure.springSecurityAmego.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.springSecurityAmego.model.Student;

@RequestMapping("api/v1/students")
@RestController   
public class StudentController {
	
	
	
	/*end point for any free access*/
	
	@GetMapping(value= "/index")
	public String greeting() {
		return "Hello , welcome to the spring security class";
	}  
	

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "gyan1"), 
			new Student(2, "gyan2"),
			new Student(3, "gyan3"));
	
	

	@GetMapping(value = "{sudentId}")
	public Student getStudent(@PathVariable("sudentId") Integer studentId) {
		
		return STUDENTS.stream()
				.filter(student -> student.getStudentID().equals(studentId))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("student with id" + studentId + " does not exists"));

	}

}
