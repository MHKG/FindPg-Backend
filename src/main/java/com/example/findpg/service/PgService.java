package com.example.findpg.service;

import com.example.findpg.DAO.PgDAO;
import com.example.findpg.entity.Amenities;
import com.example.findpg.entity.Pg;

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
public class PgService implements PgDAO {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @Override
    public boolean addPG(Pg pg) {
        Query query = entityManager.createNativeQuery(environment.getProperty("addPG"));
        query.setParameter("owner_id", pg.getOwner_id());
        query.setParameter("pg_name", pg.getPg_name());
        query.setParameter("location", pg.getLocation());
        query.setParameter("state", pg.getState());
        query.setParameter("city", pg.getCity());

        query.executeUpdate();
        return true;
    }

    @Override
    public boolean updatePG(Pg pg) {
        Query query = entityManager.createNativeQuery(environment.getProperty("updatePG"));
        query.setParameter("pg_id", pg.getPg_id());
        query.setParameter("pg_name", pg.getPg_name());
        query.setParameter("location", pg.getLocation());
        query.setParameter("state", pg.getState());
        query.setParameter("city", pg.getCity());

        query.executeUpdate();
        return true;
    }

    @Override
    public long getTotalCountByLocation(
            String location,
            Amenities amenities,
            String filterPgType,
            int minValue,
            int maxValue,
            String filterSharingType) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getTotalCountByLocation"))
                        .setParameter("location", location)
                        .setParameter("pg_type", filterPgType)
                        .setParameter("wifi", amenities.getWifi())
                        .setParameter("food", amenities.getFood())
                        .setParameter("power_backup", amenities.getPower_backup())
                        .setParameter("parking", amenities.getParking())
                        .setParameter("attached_washroom", amenities.getAttached_washroom())
                        .setParameter("air_conditioner", amenities.getAir_conditioner())
                        .setParameter("washing_machine", amenities.getWashing_machine())
                        .setParameter("room_type", filterSharingType)
                        .setParameter("min", minValue)
                        .setParameter("max", maxValue);
        return ((Number) query.getSingleResult()).longValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pg> getAllPGByLocation(
            String location,
            Amenities amenities,
            String filterPgType,
            int minValue,
            int maxValue,
            String filterSharingType) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getAllPGByLocation"))
                        .setParameter("location", location)
                        .setParameter("pg_type", filterPgType)
                        .setParameter("wifi", amenities.getWifi())
                        .setParameter("food", amenities.getFood())
                        .setParameter("power_backup", amenities.getPower_backup())
                        .setParameter("parking", amenities.getParking())
                        .setParameter("attached_washroom", amenities.getAttached_washroom())
                        .setParameter("air_conditioner", amenities.getAir_conditioner())
                        .setParameter("washing_machine", amenities.getWashing_machine())
                        .setParameter("room_type", filterSharingType)
                        .setParameter("min", minValue)
                        .setParameter("max", maxValue);
        return (List<Pg>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getPgId(int owner_id, String pg_name, String location) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getPgId"))
                        .setParameter("owner_id", owner_id)
                        .setParameter("pg_name", pg_name)
                        .setParameter("location", location);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAllLocations(String location) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getAllLocations"))
                        .setParameter("location", location);

        return (List<String>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pg> getByPgId(String pg_id) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getPgByPgId"), Pg.class)
                        .setParameter("pg_id", pg_id);
        return (List<Pg>) query.getResultList();
    }

    @Override
    public boolean updatePgTypeAndDesc(String pg_id, String type, String description) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("updatePgTypeAndDesc"))
                        .setParameter("pg_id", pg_id)
                        .setParameter("pg_type", type)
                        .setParameter("description", description);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean updatePgPrices(
            String pgId, String deposit, String electricCharges, String maintenance) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("updatePgPrices"))
                        .setParameter("pg_id", pgId)
                        .setParameter("deposit", deposit)
                        .setParameter("electric_charges", electricCharges)
                        .setParameter("maintenance", maintenance);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean addContactPersonDetails(
            String pgId, String contactPersonName, String contactPersonPhoneNumber) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("addContactPersonDetails"))
                        .setParameter("pg_id", pgId)
                        .setParameter("contact_person_name", contactPersonName)
                        .setParameter("contact_person_phone_number", contactPersonPhoneNumber);

        query.executeUpdate();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pg> getAllBySimilarity(String pgType, String location) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getAllBySimilarity"))
                        .setParameter("location", location)
                        .setParameter("pg_type", pgType);
        return (List<Pg>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Pg> getAllByOwnerId(String owner_id) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getAllPGByOwner"))
                        .setParameter("owner_id", owner_id);
        return (List<Pg>) query.getResultList();
    }
}
