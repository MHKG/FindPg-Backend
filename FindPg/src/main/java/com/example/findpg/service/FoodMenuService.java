package com.example.findpg.service;

import com.example.findpg.DAO.FoodMenuDAO;
import com.example.findpg.entity.FoodMenu;

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
public class FoodMenuService implements FoodMenuDAO {

    @Autowired private Environment environment;

    @PersistenceContext private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<FoodMenu> getByPgId(String pg_id) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getMenuByPgId"), FoodMenu.class)
                        .setParameter("pg_id", pg_id);
        return (List<FoodMenu>) query.getResultList();
    }

    @Override
    public boolean add(String pgId, String day, String breakfast, String lunch, String dinner) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("addFoodMenuForPg"))
                        .setParameter("pg_id", pgId)
                        .setParameter("days", day)
                        .setParameter("breakfast", breakfast)
                        .setParameter("lunch", lunch)
                        .setParameter("dinner", dinner);

        query.executeUpdate();
        return true;
    }

    @Override
    public boolean update(String pgId, String day, String breakfast, String lunch, String dinner) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("updateFoodMenuForPg"))
                        .setParameter("pg_id", pgId)
                        .setParameter("days", day)
                        .setParameter("breakfast", breakfast)
                        .setParameter("lunch", lunch)
                        .setParameter("dinner", dinner);

        query.executeUpdate();
        return true;
    }
}
