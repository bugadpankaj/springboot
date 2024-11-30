package com.sts.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.dao.CourseDao;
import com.sts.entity.Course;
import com.sts.services.CourseServices;

@Service
public class CourseServiceImpl implements CourseServices {
	
	@Autowired
	private CourseDao courseDao;

	List<Course> list;

	public CourseServiceImpl() {
		list = new ArrayList<>();
		list.add(new Course(1, "Core Java", "Core java basic course"));
		list.add(new Course(2, "Spring boot course", "Creating rest api using spring boot"));
	}

	@Override
	public List<Course> getCourses() {
		return courseDao.findAll();
	}

	@Override
	public Course getCourse(int courseId) {

//		Course c = null;
//
//		for (Course course : list) {
//			if (course.getId() == courseId) {
//				c = course;
//				break;
//			}
//		}
//		return c;
		
		return courseDao.getOne(courseId);
	}

	@Override
	public Course addCourse(Course course) {
		
		 courseDao.save(course);
		 return course;
	}

	@Override
	public Course updateCourse(Course course) {
//		Course c = null;
//		for (Course courses : list) {
//			if (courses.getId() == course.getId()) {
//				courses.setTitle(course.getTitle());
//				courses.setDescription(course.getDescription());
//				c = courses;
//				break;
//			}
//		}
		return courseDao.save(course);
	}

	@Override
	public void deleteCourse(int courseId) {
//		list = this.list.stream().filter(e-> e.getId() != courseId).collect(Collectors.toList());	
		Course entity = courseDao.getOne(courseId);
		 courseDao.delete(entity);
	}

}
