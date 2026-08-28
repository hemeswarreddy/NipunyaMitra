package com.sih.nm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sih.nm.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByTrainerId(int trainerId);

    Course findByIdAndTrainerId(int id, int trainerId);

}