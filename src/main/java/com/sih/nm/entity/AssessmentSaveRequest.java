package com.sih.nm.entity;

import java.util.ArrayList;
import java.util.List;

public class AssessmentSaveRequest {

    private int learnerId;
    private String interest;
    private int score;
    private int totalQuestions;
    private double percentage;
    private String skillLevel;
    private List<Integer> recommendedCourseIds = new ArrayList<>();
    private List<String> recommendedCourseNames = new ArrayList<>();

    public int getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(int learnerId) {
        this.learnerId = learnerId;
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
}
