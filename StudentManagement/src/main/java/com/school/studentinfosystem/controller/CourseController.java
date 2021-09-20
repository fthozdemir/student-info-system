package com.school.studentinfosystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.studentinfosystem.dto.CourseDTO;
import com.school.studentinfosystem.entity.Course;
import com.school.studentinfosystem.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public Course createCourse(@RequestBody Course course) throws Exception {
		course = courseService.addCourse(course);
		return course;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public Course updateCourseById(@RequestBody Course course) throws Exception {
		course = courseService.updateCourseById(course);
		return course;
	}
	
	@RequestMapping(value="/getall", method = RequestMethod.GET)
	public List<CourseDTO> getAllCourse(){
		List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
		List<Course> courses = courseService.getAllCourses();
		for(Course c : courses) {
			CourseDTO dto = new CourseDTO();
			dto = c.populateDTO();
			courseDTOList.add(dto);
		}
		return courseDTOList;
	}
	
	@RequestMapping(value="/getbyid/{id}", method = RequestMethod.GET)
	public Course getCourseById(@PathVariable Long id){
		return courseService.getCourseById(id);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public void deleteCourse(@PathVariable Long id) {
		courseService.deleteCourseById(id);
	}
}
