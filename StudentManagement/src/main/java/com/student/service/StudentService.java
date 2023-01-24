package com.student.service;

import java.util.List;

import com.student.exception.StudentException;
import com.student.model.Student;

public interface StudentService {
	
	public Student registerStudent(Student student);
	
	public Student getStudentDetailsByEmail(String email)throws StudentException;

	public List<Student> getAllStudentDetails()throws StudentException;
	
	public Student updateStudent(Student student)throws StudentException;
	
	public Student deleteStudent(String email) throws StudentException;
	
}
