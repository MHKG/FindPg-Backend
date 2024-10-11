package com.example.findpg.service;

import com.example.findpg.DAO.RoomsDAO;
import com.example.findpg.entity.Rooms;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoomsService implements RoomsDAO {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @Override
    public boolean addRooms(int pg_id, String room_type, Float cost) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("addRooms"))
                        .setParameter("pg_id", pg_id)
                        .setParameter("room_type", room_type)
                        .setParameter("cost", cost);
        query.executeUpdate();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Rooms> getRoomsByPgId(String pg_id) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getRoomsByPgId"), Rooms.class)
                        .setParameter("pg_id", pg_id);
        return (List<Rooms>) query.getResultList();
    }

    @Override
    public boolean updateRooms(int pgId, String roomType, Float cost) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("updateRoom"))
                        .setParameter("pg_id", pgId)
                        .setParameter("room_type", roomType)
                        .setParameter("cost", cost);
        query.executeUpdate();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Rooms> checkForRoomType(int pgId, String room_type) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("checkForRoomType"), Rooms.class)
                        .setParameter("pg_id", pgId)
                        .setParameter("room_type", room_type);
        return (List<Rooms>) query.getResultList();
    }
}
