package com.supplynext.company_api.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends GlobalRecord{

    //COMP-0111
    private String companyId;

    private String companyName;
    private String legalName;

    private String gstNumber;
    private String pancardNumber;
    private String cinNumber;
    private String companyType; // supplier, brand owner
    private String companyLogoUrl;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String country;
    private int pincode;

    private String geoLatitude;
    private String geoLongitude;
    private boolean isKYCCompleted;

    private String supportEmail;
    private String supportPhone;

    private String bankAccountNumber;
    private String bankName;

    private String ifscCode;
    private String branchName;
    private String creditLimitForDistributors;

    @OneToMany
    private List<Document> documentList;
    private String status;

}
