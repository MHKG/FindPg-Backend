package com.example.findpg.entity;

import jakarta.persistence.*;

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

    public int getPg_rules_id() {
        return pg_rules_id;
    }

    public void setPg_rules_id(int pg_rules_id) {
        this.pg_rules_id = pg_rules_id;
    }

    public int getPg_id() {
        return pg_id;
    }

    public void setPg_id(int pg_id) {
        this.pg_id = pg_id;
    }

    public String getNotice_period() {
        return notice_period;
    }

    public void setNotice_period(String notice_period) {
        this.notice_period = notice_period;
    }

    public String getGate_close_time() {
        return gate_close_time;
    }

    public void setGate_close_time(String gate_close_time) {
        this.gate_close_time = gate_close_time;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getDrinking() {
        return drinking;
    }

    public void setDrinking(String drinking) {
        this.drinking = drinking;
    }

    public String getLoud_music() {
        return loud_music;
    }

    public void setLoud_music(String loud_music) {
        this.loud_music = loud_music;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getVisitor_entry() {
        return visitor_entry;
    }

    public void setVisitor_entry(String visitor_entry) {
        this.visitor_entry = visitor_entry;
    }
}
