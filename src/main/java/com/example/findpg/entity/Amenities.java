package com.example.findpg.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
}
