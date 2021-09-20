package com.school.studentinfosystem.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.studentinfosystem.entity.Student;
import com.school.studentinfosystem.repository.StudentRepository;
import com.school.studentinfosystem.util.PasswordUtil;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public Student addStudent(Student student) {
		student.setCreatedDate(new Timestamp(new Date().getTime()));
		student.setModifiedDate(student.getCreatedDate());
		student.setRole("USER");
		student.setPassword(PasswordUtil.bycrypt(student.getPassword()));
		return studentRepo.save(student);
	}
	
	@Override
	public Student getStudentById(Long id) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students =  (List<Student>) studentRepo.findByRole("USER");
		return students;
	}

	@Override
	public Student updateStudent(Student student) {
		Student existing = studentRepo.findById(student.getStudentId()).orElseThrow(() -> new
				  IllegalArgumentException("Invalid user id: "+student.getStudentId()));
		boolean isUpdate = false;
		if(student.getName()!= null && !student.getName().isEmpty()) {
			existing.setName(student.getName());
			isUpdate = true;
		}
		if(student.getSurname()!= null && !student.getSurname().isEmpty()) {
			existing.setSurname(student.getSurname());
			isUpdate = true;
		}
		if(student.getUserid()!= null && !student.getUserid().isEmpty()) {
			existing.setUserid(student.getUserid());
			isUpdate = true;
		}
		if(student.getPassword()!= null && !student.getPassword().isEmpty()) {
			existing.setPassword(PasswordUtil.bycrypt(student.getPassword()));
			isUpdate = true;
		}
		if(isUpdate) {
			existing.setModifiedDate(new Timestamp(new Date().getTime()));
			return studentRepo.save(existing);
		}
		
		return existing;
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepo.deleteById(id);		
	}

	@Override
	public Student getByUserid(String userid) {
		List<Student> result = studentRepo.findByUserid(userid);
		return result.get(0);
	}

}
