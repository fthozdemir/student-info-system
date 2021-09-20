package com.school.studentinfosystem.dto;

public class StudentDTO {

	private Long studentId;
	private String userid;
	private String name;
	private String surname;
	private String password;
	private Long[] courseIds;
	private String courseIdsString;

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

	public Long[] getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(Long[] courseIds) {
		this.courseIds = courseIds;
	}

	public String getCourseIdsString() {
		return courseIdsString;
	}

	public void setCourseIdsString(String courseIdsString) {
		this.courseIdsString = courseIdsString;
	}
}
