package com.student.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.student.model.Student;
import com.student.repo.StudentRepo;

@Component
public class GetCurrentUser {

	
	@Autowired
	private StudentRepo srepo;
	
	public Student getLoggedInUserDetails() {
		
		SecurityContext sc =SecurityContextHolder.getContext();
		
		Authentication auth=sc.getAuthentication();
		
		return srepo.findByEmail(auth.getName()).get();
		
	}
}
