package com.secure.springSecurityAmego.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secure.springSecurityAmego.model.Student;

@RequestMapping("management/api/v1/students")
@RestController   
public class StudentManagementController { 

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "gyan1"), 
			new Student(2, "gyan2"),
			new Student(3, "gyan3"));
	
	
	/*retreiving all the students */
	@GetMapping 
	public List<Student> getAllStudents() {
		return STUDENTS ;
	}  
	
	
	/*registering a student*/
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println(student);
	}
	
	/*delete student based on the id input*/
	@DeleteMapping(value = "{StudentId}")
	public void deleteStudent( @PathVariable("StudentId") Integer StudentId) {
		System.out.println(StudentId);
	}
	
	
	/*update student by id */
	@PutMapping(value="{StudentId}")
	public void updateStudent(@PathVariable("StudentId") Integer StudentId,@RequestBody Student student) {
		System.out.println(student);
	}
	
	@GetMapping(value = "{sudentId}")
	public Student getStudent(@PathVariable("sudentId") Integer studentId) {
		
		return STUDENTS.stream()
				.filter(student -> student.getStudentID().equals(studentId))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("student with id" + studentId + " does not exists"));

	}

}
