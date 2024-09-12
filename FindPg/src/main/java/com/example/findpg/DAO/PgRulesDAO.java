package com.example.findpg.DAO;

import com.example.findpg.entity.PgRules;

import java.util.List;

public interface PgRulesDAO {
    boolean addRules(PgRules pgRules);

    List<PgRules> getByPgId(String pg_id);

    boolean updateRules(PgRules pgRules);
}
