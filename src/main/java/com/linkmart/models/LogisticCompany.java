package com.linkmart.models;

import jakarta.persistence.*;


import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "logistic_company")
public class LogisticCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_url")
    private String companyUrl;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "logisticCompany",
            cascade={CascadeType.REMOVE, CascadeType.MERGE})
    private List<Orders> orders;


}
