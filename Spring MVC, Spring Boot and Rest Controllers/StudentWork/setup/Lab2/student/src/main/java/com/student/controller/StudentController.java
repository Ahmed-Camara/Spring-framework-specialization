package com.student.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
public class StudentController {
	
	@Inject
	private StudentService studentService;
	
	@Inject
	private Environment environment;
	
	@Inject private StudentProperties studentProperties;
	
	@GetMapping("msg")
	public String getMessage(@RequestHeader("user-agent")String userAgent) {
		
		return studentProperties.getGreeting()+" "+userAgent;
	}
	
	@GetMapping
	public Collection<Student> getAll(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") long id){
		
		return studentService.get(id);
	}
	
	@GetMapping("/single")
	public Student getSingleStudent(@RequestParam("id") Optional<Long> optional) {
		return studentService.get(optional.orElse(1l));
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		studentService.add(student);
		if(student.getId() > 0) {
			
			URI uri = URI.create("/college/student/"+student.getId());
			
			System.out.println(uri);
			
			return ResponseEntity.accepted().location(uri).build();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/test")
	public String goHome() {
		
		String title = environment.getProperty("salutation");
		String javaVersion = environment.getProperty("java.runtime.version");
		
		return title+" "+javaVersion;
	}
}
