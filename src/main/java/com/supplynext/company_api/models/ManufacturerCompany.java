package com.supplynext.company_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manufacturer_company")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerCompany extends  Company{
}
