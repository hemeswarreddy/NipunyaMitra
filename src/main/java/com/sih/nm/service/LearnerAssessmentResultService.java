package com.sih.nm.service;

import java.util.List;

import com.sih.nm.entity.LearnerAssessmentResult;

public interface LearnerAssessmentResultService {

    LearnerAssessmentResult saveAssessmentResult(LearnerAssessmentResult result);

    List<LearnerAssessmentResult> getAssessmentResultsByLearnerId(int learnerId);
}
