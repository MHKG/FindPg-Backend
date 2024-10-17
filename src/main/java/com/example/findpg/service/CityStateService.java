package com.example.findpg.service;

import com.example.findpg.DAO.CityStateDAO;
import com.example.findpg.entity.CityState;

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
public class CityStateService implements CityStateDAO {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @SuppressWarnings("unchecked")
    @Override
    public List<CityState> getAll() {
        Query query = entityManager.createNativeQuery("select * from cities");
        return query.getResultList();
    }
}
