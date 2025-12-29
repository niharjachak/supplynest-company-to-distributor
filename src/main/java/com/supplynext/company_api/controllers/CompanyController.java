package com.supplynext.company_api.controllers;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;

@RestController
@RequestMapping("/c2d/api/v1/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;


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
            try{
                CompanyOnboardingRequestDto companyOnboardingRequestDto =objectMapper.readValue(companyDetails, CompanyOnboardingRequestDto.class);


                companyService.startOnboarding(gstCertificate,panCard,companyRegistrationDocument,companyLogo,companyOnboardingRequestDto);
                HashMap<String,String> response= new HashMap<>();
                response.put("message", "Company Details uploaded successfully");
                log.info("Company registered successfully");
                return new ResponseEntity(response,HttpStatus.CREATED);

            }catch(Exception e){
                log.error(e.getStackTrace().toString());
                HashMap<String,String> response= new HashMap<>();
                response.put("message", "System Failure");
                return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);

            }

    }


}
