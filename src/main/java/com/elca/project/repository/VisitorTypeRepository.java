package com.elca.project.repository;

import com.elca.project.entity.VisitorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorTypeRepository extends JpaRepository<VisitorType, Long> {
}
