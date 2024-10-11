package com.example.findpg.repository;

import com.example.findpg.entity.Amenities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
    @Query("SELECT a FROM Amenities a WHERE a.pg_id = :pg_id")
    Amenities findByPgId(int pg_id);
}
