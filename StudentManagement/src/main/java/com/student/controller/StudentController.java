package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.exception.StudentException;
import com.student.model.Student;
import com.student.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService ss;
	
	@Autowired
	private PasswordEncoder pe;
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<>("welcome",HttpStatus.OK);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		
		student.setPassword(pe.encode(student.getPassword()));
		
		Student savedStudent=ss.registerStudent(student);
		
		return new ResponseEntity<>(savedStudent,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/students/{email}")
	public ResponseEntity<Student> getStudentByEmailHandler(@PathVariable("email") String email) throws StudentException{
		
		Student student=ss.getStudentDetailsByEmail(email);
		
		return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudentHandler() throws StudentException{
		
		List<Student> list=ss.getAllStudentDetails();
		
		return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/students")
	public ResponseEntity<Student> updateStudentHandler(@RequestBody @Valid Student student) throws StudentException{
		student.setPassword(pe.encode(student.getPassword()));
		Student stu=ss.updateStudent(student);
		
		return new ResponseEntity<>(stu,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/students/{email}")
	public ResponseEntity<Student> getCustomerByEmailHandler(@PathVariable("email") String email) throws StudentException{
		
		Student student=ss.deleteStudent(email);
		
		return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
		
	}
	

}
