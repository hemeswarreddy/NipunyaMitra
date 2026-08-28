package com.sih.nm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sih.nm.entity.Course;
import com.sih.nm.entity.Trainer;
import com.sih.nm.service.TrainerService;

@RestController
@RequestMapping("/trainer")
@CrossOrigin(origins = "*")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;


    // Home
    @GetMapping
    public ResponseEntity<String> home() {

        return ResponseEntity.ok("Trainer Controller Working");
    }


    // Trainer Login
    @PostMapping("/login")
    public ResponseEntity<?> loginTrainer(@RequestBody Trainer trainer) {

        try {

            Trainer t = trainerService.verifyTrainerLogin(
                    trainer.getUsername(),
                    trainer.getPassword()
            );

            if (t != null) {

                return ResponseEntity.ok(t);

            } else {

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid Username or Password");
            }

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login Failed : " + e.getMessage());
        }
    }


    // Trainer Profile
    @GetMapping("/profile")
    public ResponseEntity<?> displayTrainerProfile(@RequestParam int id) {

        Trainer trainer = trainerService.displayTrainerById(id);

        if (trainer != null) {

            return ResponseEntity.ok(trainer);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trainer Id not found");
        }
    }


    // Create Course
    @PostMapping("/course/create")
    public ResponseEntity<String> createCourse(
            @RequestBody Course course,
            @RequestParam int trainerId) {

        try {

            String message =
                    trainerService.createCourse(course, trainerId);

            if (message.equals("Trainer Id not found")) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(message);
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(message);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Course Creation Failed: " + e.getMessage());
        }
    }


    // Display Trainer's Courses
    @GetMapping("/course/displayall")
    public ResponseEntity<List<Course>> displayAllCourses(
            @RequestParam int trainerId) {

        List<Course> courses =
                trainerService.displayAllCourses(trainerId);

        return ResponseEntity.ok(courses);
    }


    // Update Course
    @PutMapping("/course/update")
    public ResponseEntity<?> updateCourse(
            @RequestBody Course course,
            @RequestParam int trainerId) {

        Course c =
                trainerService.updateCourse(course, trainerId);

        if (c != null) {

            return ResponseEntity.ok(c);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course Id not found for this trainer");
        }
    }


    // Delete Course
    @DeleteMapping("/course/delete")
    public ResponseEntity<String> deleteCourseById(
            @RequestParam int id,
            @RequestParam int trainerId) {

        String message =
                trainerService.deleteCourseById(id, trainerId);

        return ResponseEntity.ok(message);
    }
}