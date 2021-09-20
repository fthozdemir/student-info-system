package com.school.studentinfosystem.service;

import java.util.List;

import com.school.studentinfosystem.dto.StudentDTO;
import com.school.studentinfosystem.entity.Course;
import com.school.studentinfosystem.entity.Student;

public interface StudentManagementService {
	
	Student changePassword(StudentDTO student);
	Course addCourseToStudent(Course course, Long studentId);
	Course removeCourseFromStudent(Course course, Long studentId);
	List<Course> getAllRegisteredCourse(Long studentId);
	
}
