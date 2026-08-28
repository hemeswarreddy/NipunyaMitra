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
import com.sih.nm.service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Course Controller Working");
    }


    @PostMapping("/create")
    public ResponseEntity<String> createCourse(@RequestBody Course course) {

        try {

            String message = courseService.createCourse(course);

            return ResponseEntity.status(HttpStatus.CREATED).body(message);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Course Creation Failed: " + e.getMessage());
        }
    }


    @GetMapping("/displayall")
    public ResponseEntity<List<Course>> displayAllCourses() {

        List<Course> courses = courseService.displayAllCourses();

        return ResponseEntity.ok(courses);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@RequestBody Course course) {

        Course c = courseService.updateCourse(course);

        if (c != null) {

            return ResponseEntity.ok(c);

        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course Id not found to update");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourseById(@RequestParam int id) {

        String message = courseService.deleteCourseById(id);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}