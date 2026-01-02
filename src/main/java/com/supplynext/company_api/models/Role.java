package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends GlobalRecord{
    @Column(unique = true,nullable = false)
    private String roleId;

    @Column(unique = true)
    private String roleName;  // TCS_ADMIN, INFOSYS_SALES_MANAGER
    @ManyToMany
    List<Operation> operations;
}
