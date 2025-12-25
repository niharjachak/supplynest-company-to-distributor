package com.supplynext.company_api.service;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.repository.CompanyRepository;

import com.supplynext.company_api.utilities.MappingUtilities;
import io.imagekit.sdk.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class CompanyService {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private MappingUtilities mappingUtilities;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyEmployeeService companyEmployeeService;


    // uploads all these files on ImageKit
    public void startOnboarding(
            MultipartFile gstCertificate,
            MultipartFile panCard,
            MultipartFile companyRegistrationDocument,
            MultipartFile companyLogo,
            CompanyOnboardingRequestDto companyOnboardingRequestDto
    ) {
        //we need to upload these documents on ImageKit
        //1.Create CompanyRecord
        //2.Create a CompanyAdminAccount
        //3.Create Role and Operation
        //4.Sending mail
        Company company = mappingUtilities.mapcompanyOnboardingRequestDtoToCompany(companyOnboardingRequestDto);
        company= this.saveCompany(company);

        // Creating an admin account for the company
        CompanyEmployee admin = companyEmployeeService.createFirstAdminAccount(company);
//      documentService.uploadDocument(gstCertificate,"gstCertificate","gst-certificate","company-");

    }


    public Company saveCompany(Company company){
        return companyRepository.save(company);
    }
}
