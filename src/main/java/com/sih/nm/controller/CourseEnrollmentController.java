package com.sih.nm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sih.nm.entity.CourseEnrollment;
import com.sih.nm.service.CourseEnrollmentService;

@RestController
@RequestMapping("/enrollment")
@CrossOrigin(origins = "*")
public class CourseEnrollmentController {

    @Autowired
    private CourseEnrollmentService enrollmentService;


    // Home
    @GetMapping
    public ResponseEntity<String> home() {

        return ResponseEntity.ok("Course Enrollment Controller Working");
    }


    // Learner registers for a course
    @PostMapping("/register")
    public ResponseEntity<String> registerCourse(
            @RequestParam int learnerId,
            @RequestParam int courseId) {

        try {

            String message =
                    enrollmentService.registerCourse(learnerId, courseId);

            if (message.equals("Learner Id not found")
                    || message.equals("Course Id not found")) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(message);
            }

            if (message.equals("Learner already registered for this course")) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(message);
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(message);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Course Registration Failed: " + e.getMessage());
        }
    }


    // Learner views registered courses
    @GetMapping("/mycourses")
    public ResponseEntity<List<CourseEnrollment>> displayLearnerCourses(
            @RequestParam int learnerId) {

        List<CourseEnrollment> enrollments =
                enrollmentService.displayLearnerCourses(learnerId);

        return ResponseEntity.ok(enrollments);
    }


    // Trainer views learners registered in a course
    @GetMapping("/courselearners")
    public ResponseEntity<?> displayCourseLearners(
            @RequestParam int courseId,
            @RequestParam int trainerId) {

        List<CourseEnrollment> enrollments =
                enrollmentService.displayCourseLearners(
                        courseId, trainerId);

        if (enrollments == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found or course does not belong to this trainer");
        }

        return ResponseEntity.ok(enrollments);
    }
}