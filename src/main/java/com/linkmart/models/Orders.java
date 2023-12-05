package com.linkmart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    private int id;

    @OneToMany(mappedBy = "orders")
    private List<ReportCase> reportCaseList;


    //TODO: Add relation to ReportCase table
}
