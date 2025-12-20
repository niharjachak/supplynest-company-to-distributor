package com.supplynext.company_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "distributor employee")
public class DistributorEmployee extends User{

    private  String distributorEmployeeId;
    @ManyToOne
    private Distributor distributor;
}
