package com.example.findpg.DAO;

import com.example.findpg.entity.Rooms;

import java.util.List;

public interface RoomsDAO {
    boolean addRooms(int pg_id, String room_type, Float cost);

    List<Rooms> getRoomsByPgId(String pg_id);

    boolean updateRooms(int pgId, String roomType, Float cost);

    List<Rooms> checkForRoomType(int pgId, String room_type);
}
