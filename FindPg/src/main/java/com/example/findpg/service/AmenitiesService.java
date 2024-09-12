package com.example.findpg.service;

import com.example.findpg.DAO.AmenitiesDAO;
import com.example.findpg.entity.Amenities;
import com.example.findpg.repository.AmenitiesRepository;

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
public class AmenitiesService implements AmenitiesDAO {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @Autowired private AmenitiesRepository amenitiesRepository;

    @Override
    public boolean addAmenities(Amenities amenities) {
        Query query = entityManager.createNativeQuery(environment.getProperty("addAmenities"));
        query.setParameter("pg_id", amenities.getPg_id());
        query.setParameter("wifi", amenities.getWifi());
        query.setParameter("food", amenities.getFood());
        query.setParameter("power_backup", amenities.getPower_backup());
        query.setParameter("parking", amenities.getParking());
        query.setParameter("cleaning", amenities.getCleaning());
        query.setParameter("attached_washroom", amenities.getAttached_washroom());
        query.setParameter("air_conditioner", amenities.getAir_conditioner());
        query.setParameter("washing_machine", amenities.getWashing_machine());
        query.executeUpdate();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Amenities> getByPgId(String pg_id) {
        Query query =
                entityManager
                        .createNativeQuery(
                                environment.getProperty("getAmenitiesByPgId"), Amenities.class)
                        .setParameter("pg_id", pg_id);
        return (List<Amenities>) query.getResultList();
    }

    @Override
    public boolean updateAmenities(Amenities amenities) {
        Amenities amenities1 = amenitiesRepository.findByPgId(amenities.getPg_id());

        amenities1.setPg_id(amenities.getPg_id());
        amenities1.setWifi(amenities.getWifi());
        amenities1.setFood(amenities.getFood());
        amenities1.setPower_backup(amenities.getPower_backup());
        amenities1.setParking(amenities.getParking());
        amenities1.setCleaning(amenities.getCleaning());
        amenities1.setAttached_washroom(amenities.getAttached_washroom());
        amenities1.setAir_conditioner(amenities.getAir_conditioner());
        amenities1.setWashing_machine(amenities.getWashing_machine());
        amenitiesRepository.save(amenities1);
        return true;
    }
}
