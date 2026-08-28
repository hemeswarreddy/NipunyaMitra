package com.sih.nm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sih.nm.entity.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

    Trainer findByUsernameAndPassword(String username, String password);

}