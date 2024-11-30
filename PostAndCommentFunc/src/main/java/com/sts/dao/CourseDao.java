package com.sts.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sts.entity.Course;

public interface CourseDao extends JpaRepository<Course, Integer>{

}
