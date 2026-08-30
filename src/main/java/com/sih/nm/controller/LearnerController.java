package com.sih.nm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sih.nm.entity.AssessmentSaveRequest;
import com.sih.nm.entity.Learner;
import com.sih.nm.entity.LearnerAssessmentResult;
import com.sih.nm.service.LearnerAssessmentResultService;
import com.sih.nm.service.LearnerService;

@RestController
@RequestMapping("/learner")
@CrossOrigin(origins = "*")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @Autowired
    private LearnerAssessmentResultService learnerAssessmentResultService;

    @GetMapping
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Learner Controller Working");
    }

    @PostMapping("/assessment/save")
    public ResponseEntity<?> saveAssessment(@RequestBody AssessmentSaveRequest request) {
        try {
            Learner learner = new Learner();
            learner.setId(request.getLearnerId());

            LearnerAssessmentResult result = new LearnerAssessmentResult();
            result.setLearner(learner);
            result.setInterest(request.getInterest());
            result.setScore(request.getScore());
            result.setTotalQuestions(request.getTotalQuestions());
            result.setPercentage(request.getPercentage());
            result.setSkillLevel(request.getSkillLevel());
            result.setRecommendedCourseIds(request.getRecommendedCourseIds());
            result.setRecommendedCourseNames(request.getRecommendedCourseNames());

            LearnerAssessmentResult saved = learnerAssessmentResultService.saveAssessmentResult(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Assessment save failed: " + e.getMessage());
        }
    }

    @GetMapping("/assessment/{learnerId}")
    public ResponseEntity<List<LearnerAssessmentResult>> getAssessmentHistory(@PathVariable int learnerId) {
        return ResponseEntity.ok(learnerAssessmentResultService.getAssessmentResultsByLearnerId(learnerId));
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