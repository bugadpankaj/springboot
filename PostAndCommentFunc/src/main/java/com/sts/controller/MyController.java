package com.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sts.entity.Course;
import com.sts.services.CourseServices;

@RestController
public class MyController {
	
	@Autowired(required = true)
	private CourseServices courseServices;

	@GetMapping("/courses")
	public List<Course> getCourses() {
		
		return this.courseServices.getCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	public Course getCourse(@PathVariable int courseId) {
		return this.courseServices.getCourse(courseId);
	}
	
	@PostMapping("/courses")
	public Course addCourse(@RequestBody Course course) {
		return this.courseServices.addCourse(course);
	}
	
	@PutMapping("/courses")
	public Course updateCourse(@RequestBody Course course) {
		return this.courseServices.updateCourse(course);
	}
	
	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable int courseId){
		try {
			 this.courseServices.deleteCourse(courseId);
			 return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
