package com.school.studentinfosystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.school.studentinfosystem.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{
		
	List<Course> findByStudents_studentId(Long studentId);
}
