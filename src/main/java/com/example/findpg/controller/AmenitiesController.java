package com.example.findpg.controller;

import com.example.findpg.DAO.AmenitiesDAO;
import com.example.findpg.GenericActionResponse.GenericActionResponse;
import com.example.findpg.entity.Amenities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities_controller")
public class AmenitiesController {

    @Autowired private AmenitiesDAO amenitiesDAO;

    @PostMapping("/add")
    private GenericActionResponse<Amenities> add(@RequestBody Amenities amenities) {
        GenericActionResponse<Amenities> response = new GenericActionResponse<>(false);

        boolean isAdded;

        List<Amenities> checkIfAlreadyExisted =
                amenitiesDAO.getByPgId(String.valueOf(amenities.getPg_id()));
        if (checkIfAlreadyExisted.isEmpty()) {
            isAdded = amenitiesDAO.addAmenities(amenities);
        } else {
            isAdded = amenitiesDAO.updateAmenities(amenities);
        }

        if (isAdded) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setErrmsg("Failed");
        }
        return response;
    }

    @PostMapping("/getByPgId")
    public List<Amenities> getByPgId(@RequestParam("pg_id") String pg_id) {
        return amenitiesDAO.getByPgId(pg_id);
    }
}
