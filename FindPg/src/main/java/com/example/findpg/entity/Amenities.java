package com.example.findpg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "amenities")
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int amenities_id;

    @Column private int pg_id;
    @Column private String wifi;
    @Column private String food;
    @Column private String power_backup;
    @Column private String parking;
    @Column private String cleaning;
    @Column private String attached_washroom;
    @Column private String air_conditioner;
    @Column private String washing_machine;

    public Amenities() {}

    public Amenities(
            String wifi,
            String food,
            String power_backup,
            String parking,
            String attached_washroom,
            String air_conditioner,
            String washing_machine) {
        this.wifi = wifi;
        this.food = food;
        this.power_backup = power_backup;
        this.parking = parking;
        this.attached_washroom = attached_washroom;
        this.air_conditioner = air_conditioner;
        this.washing_machine = washing_machine;
    }

    public int getAmenities_id() {
        return amenities_id;
    }

    public void setAmenities_id(int amenities_id) {
        this.amenities_id = amenities_id;
    }

    public int getPg_id() {
        return pg_id;
    }

    public void setPg_id(int pg_id) {
        this.pg_id = pg_id;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getPower_backup() {
        return power_backup;
    }

    public void setPower_backup(String power_backup) {
        this.power_backup = power_backup;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getCleaning() {
        return cleaning;
    }

    public void setCleaning(String cleaning) {
        this.cleaning = cleaning;
    }

    public String getAttached_washroom() {
        return attached_washroom;
    }

    public void setAttached_washroom(String attached_washroom) {
        this.attached_washroom = attached_washroom;
    }

    public String getAir_conditioner() {
        return air_conditioner;
    }

    public void setAir_conditioner(String air_conditioner) {
        this.air_conditioner = air_conditioner;
    }

    public String getWashing_machine() {
        return washing_machine;
    }

    public void setWashing_machine(String washing_machine) {
        this.washing_machine = washing_machine;
    }
}
