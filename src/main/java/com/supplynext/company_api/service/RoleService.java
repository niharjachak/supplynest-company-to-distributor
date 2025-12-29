package com.supplynext.company_api.service;

import com.supplynext.company_api.models.Company;
import com.supplynext.company_api.models.Role;
import com.supplynext.company_api.models.User;
import com.supplynext.company_api.repository.RoleRepository;
import com.supplynext.company_api.utilities.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private OperationService operationService;

    @Autowired
    private RoleRepository roleRepository;

    // the bot will have access to all the methods/features
    public Role createBotRole(){
        Role role = new Role();
        role.setRoleId(CommonUtility.generateIdForEntity("ROLE"));
        role.setRoleName("SUPPLYNEXT_BOT");
        role.setOperations(operationService.getAllOperations());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        // we cannot set createdBy and updatedBY as this is the first role
        return this.saveRole(role);
    }

    public Role saveRole(Role role){
       return roleRepository.save(role);
    }

    public Role createFirstAdminRoleForCompany(Company company, User botUser){
        Role role = new Role();
        role.setRoleId(CommonUtility.generateIdForEntity("ROLE"));
        role.setRoleName(company.getLegalName()+"_"+"Admin");
        role.setOperations(operationService.getAllOperations());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        role.setCreatedBy(botUser);
        role.setUpdatedBy(List.of(botUser));

        return this.saveRole(role);
    }


}
