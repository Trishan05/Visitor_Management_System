package com.elca.project.repository;

import com.elca.project.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends PagingAndSortingRepository<Visit, Long>, QuerydslPredicateExecutor<Visit> {
}
