package com.sih.nm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.nm.entity.Course;
import com.sih.nm.entity.Trainer;
import com.sih.nm.repository.CourseRepository;
import com.sih.nm.repository.TrainerRepository;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Trainer verifyTrainerLogin(String username, String password) {

        return trainerRepository.findByUsernameAndPassword(username, password);
    }


    @Override
    public Trainer displayTrainerById(int id) {

        Optional<Trainer> optional = trainerRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }


    @Override
    public String createCourse(Course course, int trainerId) {

        Optional<Trainer> optional = trainerRepository.findById(trainerId);

        if (optional.isPresent()) {

            course.setTrainer(optional.get());

            courseRepository.save(course);

            return "Course created successfully";

        } else {

            return "Trainer Id not found";
        }
    }


    @Override
    public List<Course> displayAllCourses(int trainerId) {

        return courseRepository.findByTrainerId(trainerId);
    }


    @Override
    public Course updateCourse(Course course, int trainerId) {

        Course existingCourse =
                courseRepository.findByIdAndTrainerId(course.getId(), trainerId);

        if (existingCourse != null) {

            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setDuration(course.getDuration());
            existingCourse.setLevel(course.getLevel());

            return courseRepository.save(existingCourse);

        } else {

            return null;
        }
    }


    @Override
    public String deleteCourseById(int id, int trainerId) {

        Course course =
                courseRepository.findByIdAndTrainerId(id, trainerId);

        if (course != null) {

            courseRepository.delete(course);

            return "Course deleted successfully";

        } else {

            return "Course Id not found for this trainer";
        }
    }
}