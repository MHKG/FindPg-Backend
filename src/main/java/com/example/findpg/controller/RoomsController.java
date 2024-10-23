package com.example.findpg.controller;

import com.example.findpg.DAO.RoomsDAO;
import com.example.findpg.entity.Rooms;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms_controller")
public class RoomsController {

    @Autowired private RoomsDAO roomsDAO;

    @PostMapping("/add")
    public ResponseEntity<List<Rooms>> addRooms(@RequestBody String jsonData) {
        List<Rooms> rooms;
        List<Rooms> finalList = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            boolean isAdded;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject roomData = jsonArray.getJSONObject(i);
                int pg_id = roomData.getInt("pg_id");
                String room_type = roomData.getString("room_type");
                Float cost = Float.valueOf(roomData.getString("cost"));

                rooms = roomsDAO.getRoomsByPgId(String.valueOf(pg_id));

                List<Rooms> roomTypes = roomsDAO.checkForRoomType(pg_id, room_type);
                if (!rooms.isEmpty() && !roomTypes.isEmpty()) {
                    isAdded = roomsDAO.updateRooms(pg_id, room_type, cost);
                } else {
                    isAdded = roomsDAO.addRooms(pg_id, room_type, cost);
                }

                if (!isAdded) {
                    return new ResponseEntity<>(rooms, HttpStatus.OK);
                }
                finalList = roomsDAO.getRoomsByPgId(String.valueOf(pg_id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(finalList, HttpStatus.OK);
    }

    @PostMapping("/getByPgId")
    public List<Rooms> getByPgId(@RequestParam("pg_id") String pg_id) {
        return roomsDAO.getRoomsByPgId(pg_id);
    }
}
