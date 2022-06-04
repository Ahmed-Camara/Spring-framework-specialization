package com.student.controller;

 
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;
 
@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
	@Inject
	private StudentService studentService;
	@Inject
	private StudentProperties studentProperties;
	
	@Value("${greeting}")
	private String message;
 
	
	@GetMapping("/msg")
	public String getMessage(@RequestHeader("user-agent") String userAgent) {
		return studentProperties.getGreeting() +" : "+userAgent;
	}
	
	
	@GetMapping
	public Collection<Student> getAll(){
		
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}
	
	@GetMapping(path="/single",produces= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public Student getSingleStudent(@RequestParam("id") Optional<Long> optional) {
		return studentService.get(optional.orElse(1l));
	}
	
	@GetMapping("/search/{departement}")
	public Collection<Student> getAllStudentsInDepartement(@PathVariable("departement")String departement,
			@RequestParam("name") Optional<String> optional){
		
		return studentService.getAllStudentsInDepartement(departement, 
				optional.orElse(""));
	}
	
	@PostMapping
	public ResponseEntity<String> addStudent(@RequestBody Student student){
		
		studentService.add(student);
		
		if(student.getId() > 0) {
			URI addStudent = URI.create("/college/student/"+student.getId());
			return ResponseEntity.accepted().location(addStudent).build();
		}
		
		return ResponseEntity.badRequest().build();
	}

	
 

}
