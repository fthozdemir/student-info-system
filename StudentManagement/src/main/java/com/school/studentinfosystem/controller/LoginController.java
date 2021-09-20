package com.school.studentinfosystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.studentinfosystem.entity.Student;
import com.school.studentinfosystem.service.StudentService;

@RestController
@CrossOrigin
public class LoginController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ResponseEntity<String> login(){
		return ResponseEntity.ok("");
	}
	
	@RequestMapping(value="/studlogin", method = RequestMethod.GET)
	public Student studLogin(){
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 UserDetails userDetails = (UserDetails) auth.getPrincipal();
		 return studentService.getByUserid(userDetails.getUsername());
	}
}
