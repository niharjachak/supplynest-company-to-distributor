package com.supplynext.company_api.service;

import com.supplynext.company_api.dto.CompanyLoginResp;
import com.supplynext.company_api.dto.UserLoginDto;
import com.supplynext.company_api.dto.UserLoginRespDto;
import com.supplynext.company_api.exceptions.InvalidLoginCredentialsException;
import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyEmployeeService companyEmployeeService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserLoginRespDto loginUser(UserLoginDto userLoginDto){
        String email = userLoginDto.getEmail();
        String password= userLoginDto.getPassword();

        User user = userService.getUserByEmail(email);
        if(user.getPassword().equals(password)){
            Company userCompany = companyEmployeeService.getEmployeeCompanyDetails(user.getSysId());
            UserLoginRespDto respDto= new UserLoginRespDto();
            respDto.setFullName(user.getFullName());
            respDto.setEmail(user.getEmail());
            respDto.setPhoneNumber(user.getPhoneNumber());
            respDto.setAddressLine1(user.getAddressLine1());
            respDto.setAddressLine2(user.getAddressLine2());
            respDto.setAddressLine3(user.getAddressLine3());
            respDto.setPincode(user.getPincode());
            respDto.setRoles(user.getRoles());
            respDto.setCompanyUser(true);
            CompanyLoginResp companyLoginResp = new CompanyLoginResp();
            companyLoginResp.setCompanyId(userCompany.getCompanyId());
            companyLoginResp.setCompanyName(userCompany.getCompanyName());
            companyLoginResp.setLegalName(userCompany.getLegalName());
            companyLoginResp.setCompanyType(userCompany.getCompanyType());
            companyLoginResp.setCompanyLogoUrl(userCompany.getCompanyLogoUrl());
            companyLoginResp.setAddressLine1(userCompany.getAddressLine1());
            companyLoginResp.setAddressLine2(userCompany.getAddressLine2());
            companyLoginResp.setAddressLine3(userCompany.getAddressLine3());
            companyLoginResp.setCity(userCompany.getCity());
            companyLoginResp.setState(userCompany.getState());
            companyLoginResp.setCountry(userCompany.getCountry());
            companyLoginResp.setPincode(userCompany.getPincode());
            companyLoginResp.setGeoLatitude(userCompany.getGeoLatitude());
            companyLoginResp.setGeoLongitude(userCompany.getGeoLongitude());


            respDto.setCompanyLoginResp(companyLoginResp);

            // Generating a Jwt token after successfull login
            List<String> roleNames=this.getRoleNames(user.getRoles());
            String jwtToken =jwtUtil.generateJwtToken(user.getEmail(),roleNames);
            respDto.setToken(jwtToken);
            return respDto;
        }
        else{
            throw new InvalidLoginCredentialsException(String.format("Invalid Login Credentials entered !"));
        }
    }

    public List<String> getRoleNames (List<Role> roles){
        List<String>roleNames= new ArrayList<>();
        for(Role role: roles){
            roleNames.add(role.getRoleName());
        }
        return roleNames;
    }
}
