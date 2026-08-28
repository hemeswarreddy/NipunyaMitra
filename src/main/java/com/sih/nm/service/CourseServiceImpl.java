package com.sih.nm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.nm.entity.Course;
import com.sih.nm.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public String createCourse(Course course) {

        courseRepository.save(course);

        return "Course created successfully";
    }


    @Override
    public List<Course> displayAllCourses() {

        return courseRepository.findAll();
    }


    @Override
    public Course updateCourse(Course course) {

        Optional<Course> optional = courseRepository.findById(course.getId());

        if (optional.isPresent()) {

            Course existingCourse = optional.get();

            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setDuration(course.getDuration());
            existingCourse.setLevel(course.getLevel());

            return courseRepository.save(existingCourse);
        }
        else {

            return null;
        }
    }


    @Override
    public String deleteCourseById(int id) {

        Boolean status = courseRepository.existsById(id);

        if (status) {

            courseRepository.deleteById(id);

            return "Course deleted successfully";
        }
        else {

            return "Course Id not found to delete";
        }
    }
}