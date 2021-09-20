package com.school.studentinfosystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.studentinfosystem.dto.StudentDTO;
import com.school.studentinfosystem.entity.Student;
import com.school.studentinfosystem.service.StudentService;

@CrossOrigin
@RequestMapping("/student")
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public Student createStudent(@RequestBody Student student) throws Exception {
		student = studentService.addStudent(student);
		return student;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public Student updateStudentById(@RequestBody Student student) throws Exception {
		student = studentService.updateStudent(student);
		return student;
	}
	
	@RequestMapping(value="/getall", method = RequestMethod.GET)
	public List<StudentDTO> getAllStudents(){
		List<StudentDTO> dtoList = new ArrayList<StudentDTO>();
		List<Student> studentList = studentService.getAllStudents();
		for(Student s : studentList) {
			StudentDTO dto = new StudentDTO();
			dto = s.populateDTO();
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@RequestMapping(value="/getbyid/{id}", method = RequestMethod.GET)
	public Student getStudentById(@PathVariable Long id){
		return studentService.getStudentById(id);
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public void deleteCourse(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}
}
