package com.school.studentinfosystem.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.school.studentinfosystem.dto.CourseDTO;

@Entity
public class Course {
	
	@Id
    @GeneratedValue
    @Column(name = "course_id", nullable = false)
	Long courseId;
	
	@Column(name = "name", length = 128, nullable = false)
	private String name;
	
	@Column(name = "Created_Date", length = 36, nullable = false)
	  private Date createdDate;
	  
	@Column(name = "Modified_Date", length = 36, nullable = false)
	private Date modifiedDate;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	@JsonBackReference
	private Set<Student> students = new HashSet<>();

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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
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
	
	public CourseDTO populateDTO() {
		CourseDTO dto = new CourseDTO();
		dto.setCourseId(this.courseId);
		dto.setName(this.name);
		String studentIds = "";
		for(Student s : this.students) {
			if(s != null) {
				studentIds += s.getStudentId() + ",";
			} 
		}
		dto.setStudentIdsStr(studentIds);
		return dto;
	}
	
}
