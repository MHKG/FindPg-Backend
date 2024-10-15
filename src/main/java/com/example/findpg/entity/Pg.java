package com.example.findpg.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg")
public class Pg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int pg_id;

    @Column private int owner_id;
    @Column private String pg_name;
    @Column private String pg_type;
    @Column private String location;

    @Column(nullable = true)
    private Float latitude;

    @Column(nullable = true)
    private Float longitude;

    @Column private String description;
    @Column private float deposit;
    @Column private float maintenance;
    @Column private String electric_charges;
    @Column private String state;
    @Column private String city;
    @Column private String contact_person_name;
    @Column private String contact_person_phone_number;
}
