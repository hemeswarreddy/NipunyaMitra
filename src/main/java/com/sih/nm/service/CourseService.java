package com.sih.nm.service;

import java.util.List;

import com.sih.nm.entity.Course;

public interface CourseService {

    String createCourse(Course course);

    List<Course> displayAllCourses();

    Course updateCourse(Course course);

    String deleteCourseById(int id);
}