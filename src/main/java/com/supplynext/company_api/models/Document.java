package com.supplynext.company_api.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document  extends GlobalRecord{

    private  String documentName;
    private  String documentOriginalName;
    private  String documentType;
    private  String documentUrl;

    @ManyToOne
    private Company company;
}
