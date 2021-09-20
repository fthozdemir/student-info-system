package com.school.studentinfosystem.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.studentinfosystem.entity.Course;
import com.school.studentinfosystem.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public List<Course> getAllCourses() {
		return (List<Course>) courseRepo.findAll();
	}
	
	@Override
	public Course getCourseById(Long id) {
		Course course = courseRepo.findById(id).orElseThrow(() -> new IllegalArgumentException());
		return course;
	}

	@Override
	public Course addCourse(Course course) {
		course.setCreatedDate(new Timestamp(new Date().getTime()));
		course.setModifiedDate(course.getCreatedDate());
		return courseRepo.save(course);
	}

	@Override
	public Course updateCourseById(Course course) {
		Course existing = courseRepo.findById(course.getCourseId()).orElseThrow(() -> new
		  IllegalArgumentException("Invalid user id: "+course.getCourseId()));
		if(!course.getName().isEmpty()) {
			existing.setName(course.getName());
			existing.setModifiedDate(new Timestamp(new Date().getTime()));
			return courseRepo.save(existing);
		}
		return existing;
	}

	@Override
	public void deleteCourseById(Long id) {
		courseRepo.deleteById(id);
	}

}
