package com.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.student.model.Authority;
import com.student.model.Student;
import com.student.repo.StudentRepo;

@Service
public class StudentUserDetailsService implements UserDetailsService{

	@Autowired
	private StudentRepo sr;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Student> opt= sr.findByEmail(username);

		if(opt.isPresent()) {
			
			Student student= opt.get();
			
			List<GrantedAuthority> authorities = new ArrayList<>();
		
			List<Authority> auths= student.getAuthorities();
			
			for(Authority auth:auths) {
				SimpleGrantedAuthority sga=new SimpleGrantedAuthority(auth.getName());
				System.out.println("siga "+sga);
				authorities.add(sga);
			}
			
			System.out.println("granted authorities "+authorities);
			
			return new User(student.getEmail(), student.getPassword(), authorities);
			
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
	}

}
