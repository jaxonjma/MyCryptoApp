package com.unosquare.useroperation.repository;

import com.unosquare.useroperation.domain.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType,Long> {
}
