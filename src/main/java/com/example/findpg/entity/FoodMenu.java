package com.example.findpg.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "food_menu")
public class FoodMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int food_menu_id;

    @Column private int pg_id;
    @Column private int days_id;
    @Column private String breakfast;
    @Column private String lunch;
    @Column private String dinner;
}
