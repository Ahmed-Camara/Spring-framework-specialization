package com.student.controller;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.core.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Inject
	private StudentService studentService;
	
	@Value("${message}")
	private String message;
	
	@GetMapping("msg")
	public String getMessage() {
		
		return message;
	}
	
	@GetMapping
	public Collection<Student> getAll(){
		return studentService.getAllStudents();
	}
}
