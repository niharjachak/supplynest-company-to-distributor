package com.supplynext.company_api.service;

import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.repository.CompanyEmployeeRepository;
import com.supplynext.company_api.utilities.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyEmployeeService {

    @Autowired
    CompanyEmployeeRepository companyEmployeeRepository;
    public CompanyEmployee createFirstAdminAccount(Company company){
        CompanyEmployee companyEmployee= new CompanyEmployee();
        companyEmployee.setCompany(company);
        companyEmployee.setCompanyEmployeeId(CommonUtility.generateIdForEntity("COMP-EMP"));
        companyEmployee.setEmail(company.getSupportEmail());
        companyEmployee.setPassword(CommonUtility.generateRandomPassword(15));
        companyEmployee.setCreatedAt(LocalDateTime.now());
        companyEmployee.setUpdatedAt(LocalDateTime.now());
        companyEmployee.setAddressLine1(company.getAddressLine1());
        companyEmployee.setAddressLine2(company.getAddressLine2());
        companyEmployee.setAddressLine3(company.getAddressLine3());
        companyEmployee.setFullName(company.getCompanyName()+" "+"Admin");
        companyEmployee.setPhoneNumber(company.getSupportPhone());
        companyEmployee.setPincode(company.getPincode());

        return this.saveCompanyEmployee(companyEmployee);

    }

    public CompanyEmployee saveCompanyEmployee(CompanyEmployee companyEmployee){
        return companyEmployeeRepository.save(companyEmployee);
    }

}
