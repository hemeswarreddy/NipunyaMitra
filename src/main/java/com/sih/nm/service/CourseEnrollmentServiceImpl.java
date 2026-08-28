package com.sih.nm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.nm.entity.Course;
import com.sih.nm.entity.CourseEnrollment;
import com.sih.nm.entity.Learner;
import com.sih.nm.entity.Trainer;
import com.sih.nm.repository.CourseEnrollmentRepository;
import com.sih.nm.repository.CourseRepository;
import com.sih.nm.repository.LearnerRepository;

@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

    @Autowired
    private CourseEnrollmentRepository enrollmentRepository;

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public String registerCourse(int learnerId, int courseId) {

        Optional<Learner> learnerOptional =
                learnerRepository.findById(learnerId);

        if (!learnerOptional.isPresent()) {
            return "Learner Id not found";
        }

        Optional<Course> courseOptional =
                courseRepository.findById(courseId);

        if (!courseOptional.isPresent()) {
            return "Course Id not found";
        }

        CourseEnrollment existingEnrollment =
                enrollmentRepository.findByLearnerIdAndCourseId(
                        learnerId, courseId);

        if (existingEnrollment != null) {
            return "Learner already registered for this course";
        }

        CourseEnrollment enrollment = new CourseEnrollment();

        enrollment.setLearner(learnerOptional.get());
        enrollment.setCourse(courseOptional.get());

        enrollmentRepository.save(enrollment);

        return "Course registered successfully";
    }


    @Override
    public List<CourseEnrollment> displayLearnerCourses(int learnerId) {

        return enrollmentRepository.findByLearnerId(learnerId);
    }


    @Override
    public List<CourseEnrollment> displayCourseLearners(
            int courseId, int trainerId) {

        Optional<Course> courseOptional =
                courseRepository.findById(courseId);

        if (!courseOptional.isPresent()) {
            return null;
        }

        Course course = courseOptional.get();

        Trainer trainer = course.getTrainer();

        if (trainer == null || trainer.getId() != trainerId) {
            return null;
        }

        return enrollmentRepository.findByCourseId(courseId);
    }
}