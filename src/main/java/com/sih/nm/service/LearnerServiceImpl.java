package com.sih.nm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sih.nm.entity.Learner;
import com.sih.nm.repository.LearnerRepository;

@Service
public class LearnerServiceImpl implements LearnerService {

    @Autowired
    private LearnerRepository learnerRepository;


    @Override
    public String learnerRegistration(Learner learner) {

        learnerRepository.save(learner);

        return "Learner registered successfully";
    }


    @Override
    public Learner verifyLearnerLogin(String username, String password) {

        return learnerRepository.findByUsernameAndPassword(username, password);
    }


    @Override
    public Learner updateLearner(Learner learner) {

        Optional<Learner> optional = learnerRepository.findById(learner.getId());

        if (optional.isPresent()) {

            Learner existingLearner = optional.get();

            existingLearner.setFullName(learner.getFullName());
            existingLearner.setPassword(learner.getPassword());

            return learnerRepository.save(existingLearner);
        }
        else {
            return null;
        }
    }


    @Override
    public String deleteLearnerById(int id) {

        Boolean status = learnerRepository.existsById(id);

        if (status) {

            learnerRepository.deleteById(id);

            return "Learner deleted successfully";
        }
        else {

            return "Learner Id not found to delete";
        }
    }
}