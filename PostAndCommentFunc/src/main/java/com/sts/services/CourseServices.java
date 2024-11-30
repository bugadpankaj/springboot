package com.sts.services;

import java.util.List;

import com.sts.entity.Course;

public interface CourseServices {
	
	public List<Course> getCourses();

	public Course getCourse(int courseId);

	public Course addCourse(Course course);
	
	public Course updateCourse(Course course);
	
	public void deleteCourse(int courseId);

}
