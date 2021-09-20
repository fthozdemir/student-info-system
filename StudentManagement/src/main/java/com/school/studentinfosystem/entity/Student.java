package com.school.studentinfosystem.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.school.studentinfosystem.dto.StudentDTO;

@Entity
public class Student {
	
	@Id
    @GeneratedValue
    @Column(name = "student_id", nullable = false)
	private Long studentId;
	
	@Column(name = "userid", length = 128, nullable = false)
	private String userid;
	
	@Column(name = "name", length = 128, nullable = false)
	private String name;
	
	@Column(name = "surname", length = 128, nullable = false)
	private String surname;
	
	@Column(name = "password", length = 128, nullable = false)
	private String password;
	
	@Column(name="role", nullable = false, length=5)
	@JsonIgnore
    private String role;
	
	@Column(name = "Created_Date", length = 36, nullable = false)
	private Date createdDate;
	  
	@Column(name = "Modified_Date", length = 36, nullable = false)
	private Date modifiedDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_id") }, 
	inverseJoinColumns = {@JoinColumn(name = "course_id") })
	@JsonBackReference
	private Set<Course> courses;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public StudentDTO populateDTO() {
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(this.studentId);
		studentDTO.setName(this.name);
		studentDTO.setSurname(this.surname);
		studentDTO.setUserid(this.userid);
		studentDTO.setPassword(this.password);
		String courseIdsStr = "";
		for(Course c : this.getCourses()) {
			if(c != null) {
				courseIdsStr += c.getCourseId() + ",";
			}
		}
		studentDTO.setCourseIdsString(courseIdsStr);
		return studentDTO;
	}

}
