package com.elca.project.repository;

import com.elca.project.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long>, QuerydslPredicateExecutor <Interview> {

}
