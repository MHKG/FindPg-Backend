package com.example.findpg.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column private String name;
    @Column private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column private String imageURL;
    @Column private String address;
    @Column private String languages;
    @Column private int otp;
    @Column private String role;
    @Column private String password;
}
