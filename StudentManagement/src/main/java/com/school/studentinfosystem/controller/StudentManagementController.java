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
import com.school.studentinfosystem.dto.StudentDTO;
import com.school.studentinfosystem.entity.Course;
import com.school.studentinfosystem.entity.Student;
import com.school.studentinfosystem.service.CourseService;
import com.school.studentinfosystem.service.StudentManagementService;
import com.school.studentinfosystem.service.StudentService;

@RequestMapping("/studentmgmt")
@RestController
public class StudentManagementController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentManagementService studentManagementService;
	
	//addcoursebytostud
	//removecoursefromstud
	//getallcourse
	//getaregisteredcoursebystudid
	
	@RequestMapping(value="/changepwd", method = RequestMethod.POST)
	public Student changePassword(@RequestBody StudentDTO studentDTO) throws Exception {
		Student student = studentManagementService.changePassword(studentDTO);
		return student;
	}
	
	@RequestMapping(value="/addcourse/{studentId}", method = RequestMethod.POST)
	public Course addCourse(@RequestBody Course course, @PathVariable Long studentId) throws Exception {
		course = studentManagementService.addCourseToStudent(course, studentId);
		return course;
	}
	
	@RequestMapping(value="/registeredcourse/{studentId}", method = RequestMethod.GET)
	public List<Course> registeredCourse(@PathVariable long studentId) throws Exception {
		return studentManagementService.getAllRegisteredCourse(studentId);
	}
	
	@RequestMapping(value="/removecourse/{studentId}", method = RequestMethod.POST)
	public String removeCourse(@RequestBody Course course, @PathVariable long studentId) throws Exception {
		studentManagementService.removeCourseFromStudent(course, studentId);
		return "course removed";
	}
	
	@RequestMapping(value="/getallCourses", method = RequestMethod.GET)
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
}
