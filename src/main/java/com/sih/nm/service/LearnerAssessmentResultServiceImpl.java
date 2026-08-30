package com.sih.nm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.nm.entity.Learner;
import com.sih.nm.entity.LearnerAssessmentResult;
import com.sih.nm.repository.LearnerAssessmentResultRepository;
import com.sih.nm.repository.LearnerRepository;

@Service
public class LearnerAssessmentResultServiceImpl implements LearnerAssessmentResultService {

    @Autowired
    private LearnerAssessmentResultRepository learnerAssessmentResultRepository;

    @Autowired
    private LearnerRepository learnerRepository;

    @Override
    public LearnerAssessmentResult saveAssessmentResult(LearnerAssessmentResult result) {
        if (result == null || result.getLearner() == null || result.getLearner().getId() <= 0) {
            throw new IllegalArgumentException("Valid learner details are required to save the assessment result.");
        }

        Optional<Learner> learner = learnerRepository.findById(result.getLearner().getId());
        if (learner.isEmpty()) {
            throw new IllegalArgumentException("Learner not found for assessment result.");
        }

        result.setLearner(learner.get());
        return learnerAssessmentResultRepository.save(result);
    }

    @Override
    public List<LearnerAssessmentResult> getAssessmentResultsByLearnerId(int learnerId) {
        Optional<Learner> learner = learnerRepository.findById(learnerId);
        if (learner.isEmpty()) {
            return List.of();
        }

        return learnerAssessmentResultRepository.findByLearnerOrderByCreatedAtDesc(learner.get());
    }
}
