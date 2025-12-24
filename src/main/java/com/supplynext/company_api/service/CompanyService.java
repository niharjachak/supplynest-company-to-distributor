package com.supplynext.company_api.service;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CompanyService {

    // uploads all these files on ImageKit
    public void startOnboarding(
            MultipartFile gstCertificate,
            MultipartFile panCard,
            MultipartFile companyRegistrationDocument,
            MultipartFile companyLogo,
            CompanyOnboardingRequestDto companyOnboardingRequestDto
    ){
        //we need to upload these documents on ImageKit

    }
}
