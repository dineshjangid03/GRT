package com.student.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.config.SecurityConstants;
import com.student.dto.StudentDTO;
import com.student.model.Student;
import com.student.repo.StudentRepo;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
	
	@Autowired
	private StudentRepo sr;
	
	@GetMapping("/signIn")
	public ResponseEntity<StudentDTO> getLoggedInCustomerDetailsHandler(Authentication auth, HttpServletResponse response){
		
		 Student student= sr.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));
		
		 System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		 
		 StudentDTO s=new StudentDTO();
		 s.setAddress(student.getAddress());
		 s.setEmail(student.getEmail());
		 s.setName(student.getName());
		 
		 String str=response.getHeaders(SecurityConstants.JWT_HEADER).toString();
		 str=str.substring(1, str.length() - 1);
		 s.setToken(str);
		 
		 s.setRole(student.getAuthorities().get(0).getName());
		 
		 return new ResponseEntity<>(s, HttpStatus.ACCEPTED);
		
		
	}

}
