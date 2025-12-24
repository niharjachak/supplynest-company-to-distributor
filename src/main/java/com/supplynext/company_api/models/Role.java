package com.supplynext.company_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String roleName;
    @ManyToOne
    List<Operation> operations;
}
