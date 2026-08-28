package com.sih.nm.service;

import java.util.List;

import com.sih.nm.entity.Course;
import com.sih.nm.entity.Trainer;

public interface TrainerService {

    Trainer verifyTrainerLogin(String username, String password);

    Trainer displayTrainerById(int id);

    String createCourse(Course course, int trainerId);

    List<Course> displayAllCourses(int trainerId);

    Course updateCourse(Course course, int trainerId);

    String deleteCourseById(int id, int trainerId);
}