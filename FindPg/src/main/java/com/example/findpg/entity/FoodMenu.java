package com.example.findpg.entity;

import jakarta.persistence.*;

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

    public int getFood_menu_id() {
        return food_menu_id;
    }

    public void setFood_menu_id(int food_menu_id) {
        this.food_menu_id = food_menu_id;
    }

    public int getPg_id() {
        return pg_id;
    }

    public void setPg_id(int pg_id) {
        this.pg_id = pg_id;
    }

    public int getDays_id() {
        return days_id;
    }

    public void setDays_id(int days_id) {
        this.days_id = days_id;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}
