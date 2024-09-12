package com.example.findpg.entity;

import jakarta.persistence.*;

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

    public int getPg_id() {
        return pg_id;
    }

    public void setPg_id(int pg_id) {
        this.pg_id = pg_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getPg_name() {
        return pg_name;
    }

    public void setPg_name(String pg_name) {
        this.pg_name = pg_name;
    }

    public String getPg_type() {
        return pg_type;
    }

    public void setPg_type(String pg_type) {
        this.pg_type = pg_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public float getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(float maintenance) {
        this.maintenance = maintenance;
    }

    public String getElectric_charges() {
        return electric_charges;
    }

    public void setElectric_charges(String electric_charges) {
        this.electric_charges = electric_charges;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact_person_name() {
        return contact_person_name;
    }

    public void setContact_person_name(String contact_person_name) {
        this.contact_person_name = contact_person_name;
    }

    public String getContact_person_phone_number() {
        return contact_person_phone_number;
    }

    public void setContact_person_phone_number(String contact_person_phone_number) {
        this.contact_person_phone_number = contact_person_phone_number;
    }
}
