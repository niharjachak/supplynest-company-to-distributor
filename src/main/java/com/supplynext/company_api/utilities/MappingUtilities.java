package com.supplynext.company_api.utilities;

import com.supplynext.company_api.dto.CompanyOnboardingRequestDto;
import com.supplynext.company_api.models.Company;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MappingUtilities {

    public Company mapcompanyOnboardingRequestDtoToCompany(CompanyOnboardingRequestDto companyOnboardingRequestDto){
        Company company = new Company();

        // Primary Key
        company.setCompanyId(CommonUtility.generateIdForEntity("COMP"));

        // Basic Info
        company.setCompanyName(companyOnboardingRequestDto.getCompanyName());
        company.setLegalName(companyOnboardingRequestDto.getLegalName());
        company.setCompanyType(companyOnboardingRequestDto.getCompanyType());

        // Business & Compliance
        company.setGstNumber(companyOnboardingRequestDto.getGstNumber());
        company.setPancardNumber(companyOnboardingRequestDto.getPanNumber());
        company.setCinNumber(companyOnboardingRequestDto.getCinNumber());

        // Address
        company.setAddressLine1(companyOnboardingRequestDto.getAddressLine1());
        company.setAddressLine2(companyOnboardingRequestDto.getAddressLine2());
        company.setAddressLine3(companyOnboardingRequestDto.getAddressLine3());
        company.setCity(companyOnboardingRequestDto.getCity());
        company.setState(companyOnboardingRequestDto.getState());
        company.setCountry(companyOnboardingRequestDto.getCountry());
        company.setPincode(companyOnboardingRequestDto.getPincode());
        company.setGeoLatitude(companyOnboardingRequestDto.getGeoLatitude());
        company.setGeoLongitude(companyOnboardingRequestDto.getGeoLongitude());

        // Contact Info
        company.setSupportEmail(companyOnboardingRequestDto.getSupportEmail());
        company.setSupportPhone(companyOnboardingRequestDto.getSupportPhoneNumber());

        // Finance & Banking
        company.setBankAccountNumber(companyOnboardingRequestDto.getBankAccountNumber());
        company.setBankName(companyOnboardingRequestDto.getBankName());
        company.setIfscCode(companyOnboardingRequestDto.getIfscCode());
        company.setCreditLimitForDistributors(companyOnboardingRequestDto.getCreditLimitForDistributors());

        // System Defaults
        company.setKYCCompleted(false);
        company.setStatus("PENDING");

        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());

        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());


        return company;
    }
}
