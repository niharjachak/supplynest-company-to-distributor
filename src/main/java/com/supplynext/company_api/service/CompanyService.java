package com.supplynext.company_api.service;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.CompanyEmployee;
import com.supplynext.company_api.models.Document;
import com.supplynext.company_api.repository.CompanyRepository;

import com.supplynext.company_api.utilities.MappingUtilities;
import io.imagekit.sdk.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
    ) throws ForbiddenException, TooManyRequestsException, InternalServerException, UnauthorizedException, BadRequestException, UnknownException, IOException {
        //we need to upload these documents on ImageKit
        //1.Create CompanyRecord
        //2.Create a CompanyAdminAccount
        //3.Create Role and Operation
        //4.Sending mail
        Company company = mappingUtilities.mapcompanyOnboardingRequestDtoToCompany(companyOnboardingRequestDto);
        company= this.saveCompany(company);

        // Creating an admin account for the company
        CompanyEmployee admin = companyEmployeeService.createFirstAdminAccount(company);
        String folderName= "company/"+company.getCompanyId();

        List<Document> documents = new ArrayList<>();

        Document gstDocument= documentService.uploadDocument(gstCertificate,"gstCertificate","gst-certificate",folderName,company);
        documents.add(gstDocument);
        Document panCardDoc=documentService.uploadDocument(panCard,"panCard","pan-card",folderName,company);
        documents.add(panCardDoc);
        Document cinDoc=documentService.uploadDocument(companyRegistrationDocument,"companyRegistrationDocument","company-registration-document",folderName,company);
        documents.add(cinDoc);
        Document companyLogoDoc=documentService.uploadDocument(companyLogo,"companyLogo","company-logo",folderName,company);
        documents.add(companyLogoDoc);

        company.setDocumentList(documents);
        company.setCompanyLogoUrl(company.getCompanyLogoUrl());
        this.saveCompany(company);
      //Send a Mail to the Company an email of successful registration of Company


    }

    public Company saveCompany(Company company){
        return companyRepository.save(company);
    }


}
