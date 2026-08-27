package com.sih.nm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sih.nm.entity.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Integer> {

    Learner findByUsernameAndPassword(String username, String password);

}