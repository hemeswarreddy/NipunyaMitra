package com.sih.nm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sih.nm.entity.CourseEnrollment;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Integer> {

    CourseEnrollment findByLearnerIdAndCourseId(int learnerId, int courseId);

    List<CourseEnrollment> findByLearnerId(int learnerId);

    List<CourseEnrollment> findByCourseId(int courseId);
}