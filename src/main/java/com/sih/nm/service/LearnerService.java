package com.sih.nm.service;

import com.sih.nm.entity.Learner;

public interface LearnerService {

    String learnerRegistration(Learner learner); // for learner self-registration

    Learner verifyLearnerLogin(String username, String password);

    Learner updateLearner(Learner learner);

    String deleteLearnerById(int id);

}