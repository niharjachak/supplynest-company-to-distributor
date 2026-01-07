package com.supplynext.company_api.dto;


import com.supplynext.company_api.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespDto {

    private String fullName;
    private String email;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private List<Role> roles;
    private CompanyLoginResp companyLoginResp;
    private int pincode;
    private String token;
    private boolean isCompanyUser; // Company dashboard, else distributor dashboard
}
