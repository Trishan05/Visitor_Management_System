package com.elca.project.repository;

import com.elca.project.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> , QuerydslPredicateExecutor<Candidate> {
}
