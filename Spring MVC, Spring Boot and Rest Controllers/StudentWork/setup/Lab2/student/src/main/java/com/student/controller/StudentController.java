package com.student.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Inject
	private StudentService studentService;
	
	@Inject
	private Environment environment;
	
	@Inject private StudentProperties studentProperties;
	
	@GetMapping("msg")
	public String getMessage() {
		
		return studentProperties.getGreeting();
	}
	
	@GetMapping
	public Collection<Student> getAll(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/test")
	public String goHome() {
		
		String title = environment.getProperty("salutation");
		String javaVersion = environment.getProperty("java.runtime.version");
		
		return title+" "+javaVersion;
	}
}
