package com.example.findpg.service;

import com.example.findpg.DAO.PgRulesDAO;
import com.example.findpg.entity.PgRules;

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
public class PgRulesService implements PgRulesDAO {

    @PersistenceContext private EntityManager entityManager;

    @Autowired private Environment environment;

    @Override
    public boolean addRules(PgRules pgRules) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("addRules"))
                        .setParameter("pg_id", pgRules.getPg_id())
                        .setParameter("notice_period", pgRules.getNotice_period())
                        .setParameter("gate_close_time", pgRules.getGate_close_time())
                        .setParameter("smoking", pgRules.getSmoking())
                        .setParameter("drinking", pgRules.getDrinking())
                        .setParameter("loud_music", pgRules.getLoud_music())
                        .setParameter("party", pgRules.getParty())
                        .setParameter("visitor_entry", pgRules.getVisitor_entry());
        query.executeUpdate();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PgRules> getByPgId(String pg_id) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("getRulesByPgId"), PgRules.class)
                        .setParameter("pg_id", pg_id);
        return (List<PgRules>) query.getResultList();
    }

    @Override
    public boolean updateRules(PgRules pgRules) {
        Query query =
                entityManager
                        .createNativeQuery(environment.getProperty("updateRules"))
                        .setParameter("pg_id", pgRules.getPg_id())
                        .setParameter("notice_period", pgRules.getNotice_period())
                        .setParameter("gate_close_time", pgRules.getGate_close_time())
                        .setParameter("smoking", pgRules.getSmoking())
                        .setParameter("drinking", pgRules.getDrinking())
                        .setParameter("loud_music", pgRules.getLoud_music())
                        .setParameter("party", pgRules.getParty())
                        .setParameter("visitor_entry", pgRules.getVisitor_entry());
        query.executeUpdate();
        return true;
    }
}
