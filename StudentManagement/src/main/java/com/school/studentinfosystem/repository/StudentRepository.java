package com.school.studentinfosystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.school.studentinfosystem.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	
	List<Student> findByUserid(String userid);
	List<Student> findByRole(String role);
}
