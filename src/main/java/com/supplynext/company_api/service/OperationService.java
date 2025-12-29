package com.supplynext.company_api.service;

import com.supplynext.company_api.models.Operation;
import com.supplynext.company_api.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public List<Operation> getAllOperations(){
        return operationRepository.findAll();
    }
}
