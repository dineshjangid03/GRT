package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.exception.StudentException;
import com.student.model.Authority;
import com.student.model.Student;
import com.student.repo.StudentRepo;
import com.student.util.GetCurrentUser;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepo sr;
	
	@Autowired
	private GetCurrentUser gu;
	
	@Override
	public Student registerStudent(Student student) {
		List<Authority> au=student.getAuthorities();
		
		for(Authority a:au) {
			a.setStudent(student);
		}
		return sr.save(student);
	}

	@Override
	public Student getStudentDetailsByEmail(String email) throws StudentException {
		return sr.findByEmail(email).orElseThrow(()->new StudentException("Student not found with email "+email));
	}

	@Override
	public List<Student> getAllStudentDetails() throws StudentException {
		List<Student> list=sr.findAll();
		
		if(list.isEmpty()) {
			throw new StudentException("no student found");
		}
		
		return list;
	}

	@Override
	public Student updateStudent(Student student) throws StudentException {
		
		Optional<Student> s=sr.findByEmail(student.getEmail());
		
		if(s.isEmpty()) {
			throw new StudentException("student not found");
		}
		
		Student temp=s.get();
		
		if(student.getName()!=null) {
			temp.setName(student.getName());
		}
		if(student.getAddress()!=null) {
			temp.setAddress(student.getAddress());
		}
		if(student.getPassword()!=null) {
			temp.setPassword(student.getPassword());
		}
		
		return sr.save(temp);
	}

	@Override
	public Student deleteStudent(String email) throws StudentException {
		
		Optional<Student> s=sr.findByEmail(email);
		
		if(s.isEmpty()) {
			throw new StudentException("student not found");
		}
		
		Student temp=s.get();
		
		sr.delete(temp);
		return temp;
	}

}
