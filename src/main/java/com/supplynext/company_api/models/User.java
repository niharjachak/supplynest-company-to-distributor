package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
// All entities extending users will contain all User attributes
// i.e. The properties present in the user table will be present in the CompanyEmployeeTable and the
// Distributor Employee Table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sysId;

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    @ManyToMany
    List<Role> roles;
    private int pincode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
