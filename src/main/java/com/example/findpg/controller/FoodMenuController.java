package com.example.findpg.controller;

import com.example.findpg.DAO.FoodMenuDAO;
import com.example.findpg.entity.FoodMenu;
import com.example.findpg.genericactionresponse.GenericActionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food_menu_controller")
public class FoodMenuController {

    @Autowired private FoodMenuDAO foodMenuDAO;

    @PostMapping("/add")
    public GenericActionResponse<FoodMenu> add(
            @RequestParam("food") String food, @RequestParam("pg_id") String pg_id)
            throws JsonProcessingException {
        GenericActionResponse<FoodMenu> response = new GenericActionResponse<>(false);
        boolean isAdd = false;

        List<FoodMenu> foodMenu = getByPgId(pg_id);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, String>> menu =
                objectMapper.readValue(food, new TypeReference<>() {});
        for (Map.Entry<String, Map<String, String>> entry : menu.entrySet()) {
            String day = entry.getKey();

            Map<String, String> foodMenuMap = entry.getValue();

            String breakfast = foodMenuMap.getOrDefault("Breakfast", "");
            String lunch = foodMenuMap.getOrDefault("Lunch", "");
            String dinner = foodMenuMap.getOrDefault("Dinner", "");

            if (foodMenu.isEmpty()) {
                isAdd = foodMenuDAO.add(pg_id, day, breakfast, lunch, dinner);
            } else {
                isAdd = foodMenuDAO.update(pg_id, day, breakfast, lunch, dinner);
            }

            if (!isAdd) {
                break;
            }
        }
        response.setSuccess(isAdd);
        return response;
    }

    @PostMapping("/getByPgId")
    public List<FoodMenu> getByPgId(@RequestParam("pg_id") String pg_id) {
        return foodMenuDAO.getByPgId(pg_id);
    }
}
