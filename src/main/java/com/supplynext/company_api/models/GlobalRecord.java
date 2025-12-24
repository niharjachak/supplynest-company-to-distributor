package com.supplynext.company_api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
//The GlobalRecord table stores the common attributes of every modelthat extends it.
// It acts as a Parent table that stores the values of The COmpany, Product, Document created

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "global_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID sysId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    private User createdBy;
    @ManyToMany
    private List<User> updatedBy;




}
