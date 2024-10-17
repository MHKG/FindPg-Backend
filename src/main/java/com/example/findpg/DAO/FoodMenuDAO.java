package com.example.findpg.DAO;

import com.example.findpg.entity.FoodMenu;

import java.util.List;

public interface FoodMenuDAO {
    List<FoodMenu> getByPgId(String pg_id);

    boolean add(String pgId, String day, String breakfast, String lunch, String dinner);

    boolean update(String pgId, String day, String breakfast, String lunch, String dinner);
}
