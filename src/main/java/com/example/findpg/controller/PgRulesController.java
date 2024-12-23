package com.example.findpg.controller;

import com.example.findpg.DAO.PgRulesDAO;
import com.example.findpg.GenericActionResponse.GenericActionResponse;
import com.example.findpg.entity.PgRules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pg_rules_controller")
public class PgRulesController {

    @Autowired private PgRulesDAO pgRulesDAO;

    @PostMapping("/add")
    public GenericActionResponse<PgRules> addRules(@RequestBody PgRules pg_rules) {
        GenericActionResponse<PgRules> response = new GenericActionResponse<>(false);

        boolean isAdded;

        List<PgRules> checkIfAlreadyExisted =
                pgRulesDAO.getByPgId(String.valueOf(pg_rules.getPg_id()));
        if (checkIfAlreadyExisted.isEmpty()) {
            isAdded = pgRulesDAO.addRules(pg_rules);
        } else {
            isAdded = pgRulesDAO.updateRules(pg_rules);
        }

        if (isAdded) {
            response.setSuccess(true);
        } else {
            response.setErrmsg("Failed");
        }
        return response;
    }

    @PostMapping("/getByPgId")
    public List<PgRules> getByPgId(@RequestParam("pg_id") String pg_id) {
        return pgRulesDAO.getByPgId(pg_id);
    }
}
