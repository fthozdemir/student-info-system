package com.school.studentinfosystem.service;

import java.util.List;

import com.school.studentinfosystem.entity.Student;

public interface StudentService {
	
	Student addStudent(Student student);
	List<Student> getAllStudents();
	Student updateStudent(Student student);
	void deleteStudent(Long id);
	Student getStudentById(Long id);
	Student getByUserid(String userid);
}
