package com.sih.nm.controller;

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

import com.sih.nm.entity.Learner;
import com.sih.nm.service.LearnerService;

@RestController
@RequestMapping("/learner")
@CrossOrigin(origins = "*")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;


    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Learner Controller Working");
    }


    @PostMapping("/signup")
    public ResponseEntity<String> registerLearner(@RequestBody Learner learner) {
        try {

            String message = learnerService.learnerRegistration(learner);

            return ResponseEntity.status(HttpStatus.CREATED).body(message);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Registration Failed: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginLearner(@RequestBody Learner learner) {
        try {

            Learner l = learnerService.verifyLearnerLogin(
                    learner.getUsername(),
                    learner.getPassword()
            );

            if (l != null) {
                return ResponseEntity.ok(l);
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid Username or Password");
            }

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Login Failed : " + e.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateLearner(@RequestBody Learner learner) {

        Learner l = learnerService.updateLearner(learner);

        if (l != null) {
            return ResponseEntity.ok(l);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Learner Id not found to update");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLearnerById(@RequestParam int id) {

        String message = learnerService.deleteLearnerById(id);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}