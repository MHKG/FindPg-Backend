package com.example.findpg.controller;

import com.example.findpg.DAO.CityStateDAO;
import com.example.findpg.entity.CityState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city_state_controller")
public class CityStateController {

    @Autowired private CityStateDAO cityStateDAO;

    @GetMapping("/getAll")
    private List<CityState> getAll() {
        return cityStateDAO.getAll();
    }
}
