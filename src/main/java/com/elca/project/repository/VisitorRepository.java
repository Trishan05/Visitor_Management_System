package com.elca.project.repository;

import com.elca.project.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends PagingAndSortingRepository<Visitor, Long>, QuerydslPredicateExecutor <Visitor> {
}
