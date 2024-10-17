package com.example.findpg.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int image_id;

    @Column private String image;
    @Column private Integer pg_id;
    @Column private Integer room_id;
}
