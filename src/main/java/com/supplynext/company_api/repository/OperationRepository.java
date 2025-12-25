package com.supplynext.company_api.repository;

import com.supplynext.company_api.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation , UUID> {

    public Operation findByOperationName(String name);
    public boolean existsByOperationName(String operationName);

}
