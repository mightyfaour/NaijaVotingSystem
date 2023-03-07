package com.example.naijavotingsystem.data.repository;

import com.example.naijavotingsystem.data.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByUserId(Long userId);
    Long countByCandidateId(Long candidateId);
    Long countDistinctByUserIdIsNotNull();
}
