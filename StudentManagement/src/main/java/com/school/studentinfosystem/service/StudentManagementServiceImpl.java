package com.school.studentinfosystem.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.studentinfosystem.dto.StudentDTO;
import com.school.studentinfosystem.entity.Course;
import com.school.studentinfosystem.entity.Student;
import com.school.studentinfosystem.repository.CourseRepository;
import com.school.studentinfosystem.repository.StudentRepository;
import com.school.studentinfosystem.util.PasswordUtil;

@Service
public class StudentManagementServiceImpl implements StudentManagementService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private StudentService studService;
	
	@Override
	public Course addCourseToStudent(Course course, Long studentId) {
		course = courseRepo.findById(course.getCourseId()).
								orElseThrow(() -> new IllegalArgumentException());
		Student student = studService.getStudentById(studentId);
		course.getStudents().add(student);
		student.getCourses().add(course);
		return courseRepo.save(course);
	}

	@Override
	public Course removeCourseFromStudent(Course course, Long studentId) {
		course = courseRepo.findById(course.getCourseId()).
				orElseThrow(() -> new IllegalArgumentException());
		Student stud = studService.getStudentById(studentId);
		course.getStudents().remove(stud);
		stud.getCourses().remove(course);
		return courseRepo.save(course);
	}

	@Override
	public List<Course> getAllRegisteredCourse(Long studentId) {
		return courseRepo.findByStudents_studentId(studentId);
	}

	@Override
	public Student changePassword(StudentDTO student) {
		Student existing = studentRepo.findById(student.getStudentId()).orElseThrow(() -> new
				  IllegalArgumentException("Invalid user id: "+student.getStudentId()));
		existing.setPassword(PasswordUtil.bycrypt(student.getPassword()));
		existing.setModifiedDate(new Timestamp(new Date().getTime()));
		return studentRepo.save(existing);
	}

}
