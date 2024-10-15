package com.example.findpg.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_rules")
public class PgRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int pg_rules_id;

    @Column private int pg_id;
    @Column private String notice_period;
    @Column private String gate_close_time;
    @Column private String smoking;
    @Column private String drinking;
    @Column private String loud_music;
    @Column private String party;
    @Column private String visitor_entry;
}
