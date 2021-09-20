package com.school.studentinfosystem.dto;

public class CourseDTO {
	
	private Long courseId;
	private String name;
	private String studentIdsStr;
	
	
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentIdsStr() {
		return studentIdsStr;
	}
	public void setStudentIdsStr(String studentIdsStr) {
		this.studentIdsStr = studentIdsStr;
	}
}
