package com.sih.nm.service;

import java.util.List;

import com.sih.nm.entity.CourseEnrollment;

public interface CourseEnrollmentService {

    String registerCourse(int learnerId, int courseId);

    List<CourseEnrollment> displayLearnerCourses(int learnerId);

    List<CourseEnrollment> displayCourseLearners(int courseId, int trainerId);
}