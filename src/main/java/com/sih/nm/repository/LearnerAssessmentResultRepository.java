package com.sih.nm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sih.nm.entity.Learner;
import com.sih.nm.entity.LearnerAssessmentResult;

@Repository
public interface LearnerAssessmentResultRepository extends JpaRepository<LearnerAssessmentResult, Integer> {

    List<LearnerAssessmentResult> findByLearnerOrderByCreatedAtDesc(Learner learner);
}
