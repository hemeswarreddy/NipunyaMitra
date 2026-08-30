package com.sih.nm.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "learner_assessment_results")
public class LearnerAssessmentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learner_id", nullable = false)
    private Learner learner;

    @Column(nullable = false)
    private String interest;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int totalQuestions;

    @Column(nullable = false)
    private double percentage;

    @Column(nullable = false)
    private String skillLevel;

    @ElementCollection
    @CollectionTable(
        name = "learner_assessment_recommended_course_ids",
        joinColumns = @JoinColumn(name = "assessment_result_id")
    )
    @Column(name = "course_id")
    private List<Integer> recommendedCourseIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
        name = "learner_assessment_recommended_course_names",
        joinColumns = @JoinColumn(name = "assessment_result_id")
    )
    @Column(name = "course_name")
    private List<String> recommendedCourseNames = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Learner getLearner() {
        return learner;
    }

    public void setLearner(Learner learner) {
        this.learner = learner;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public List<Integer> getRecommendedCourseIds() {
        return recommendedCourseIds;
    }

    public void setRecommendedCourseIds(List<Integer> recommendedCourseIds) {
        this.recommendedCourseIds = recommendedCourseIds;
    }

    public List<String> getRecommendedCourseNames() {
        return recommendedCourseNames;
    }

    public void setRecommendedCourseNames(List<String> recommendedCourseNames) {
        this.recommendedCourseNames = recommendedCourseNames;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
