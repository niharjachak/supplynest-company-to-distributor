package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company_employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEmployee  extends User{

    private String companyEmployeeId;
    @ManyToOne  // many employees are associated with a single company
    private Company company;

}
