package com.example.findpg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class CityState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column private String city;
    @Column private String state;
}
