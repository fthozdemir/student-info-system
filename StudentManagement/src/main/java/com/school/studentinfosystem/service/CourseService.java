package com.school.studentinfosystem.service;

import java.util.List;

import com.school.studentinfosystem.entity.Course;

public interface CourseService {
	
	List<Course> getAllCourses();
	public Course getCourseById(Long id);
	Course addCourse(Course course);
	Course updateCourseById(Course course);
	void deleteCourseById(Long id);

}
