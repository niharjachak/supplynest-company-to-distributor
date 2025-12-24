package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/c2d/api/v1/company")
public class CompanyController {

    // maps the CompanyDetails in JSON String format
    // to CompanyOnboardingRequestDto
    ObjectMapper objectMapper = new ObjectMapper();


    // We are uploading documents to the endpoint
    // It is called as a Multipart ENdpoint
    @PostMapping("/start-onboarding")
    public ResponseEntity startOnboarding(
            @RequestPart(value = "gstCertificate") MultipartFile  gstCertificate,
            @RequestPart(value = "panCard") MultipartFile  panCard,
            @RequestPart(value = "companyRegistrationDocument")MultipartFile companyRegistrationDocument,
            @RequestPart(value = "companyLogo")MultipartFile companyLogo,
            @RequestPart(value="companyInfo") String companyDetails
            ){
                    // objectMapper reads value from The JSON string and maps the values to
                    // the CompanyOnboardingRequestDto
            CompanyOnboardingRequestDto companyOnboardingRequestDto =objectMapper.readValue(companyDetails, CompanyOnboardingRequestDto.class);
        return new ResponseEntity<>("Hello", HttpStatus.ACCEPTED);
    }


}
