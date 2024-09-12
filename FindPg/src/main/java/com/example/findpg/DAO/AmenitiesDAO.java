package com.example.findpg.DAO;

import com.example.findpg.entity.Amenities;

import java.util.List;

public interface AmenitiesDAO {
    boolean addAmenities(Amenities amenities);

    List<Amenities> getByPgId(String pgId);

    boolean updateAmenities(Amenities amenities);
}
