package com.student.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	
	public Optional<Student> findByEmail(String email);

}
