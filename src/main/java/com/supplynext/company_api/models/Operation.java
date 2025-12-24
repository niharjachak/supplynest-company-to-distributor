package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "operations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sysId;
    private String operationName;
    private  String operationType;


}
