package com.example.findpg.controller;

import com.example.findpg.DAO.PgDAO;
import com.example.findpg.entity.Amenities;
import com.example.findpg.entity.Pg;
import com.example.findpg.genericactionresponse.GenericActionResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pg_controller")
public class PgController {

    @Autowired private PgDAO pgDAO;

    @PostMapping("/add")
    private GenericActionResponse<Pg> add(@RequestBody Pg pg) {
        GenericActionResponse<Pg> response = new GenericActionResponse<>(false);

        boolean isAdded = pgDAO.addPG(pg);

        if (isAdded) {
            List<Integer> pg_id =
                    pgDAO.getPgId(pg.getOwner_id(), pg.getPg_name(), pg.getLocation());
            response.setSuccess(true);
            response.setSuccessmsg(String.valueOf(pg_id.get(pg_id.size() - 1)));
        } else {
            response.setSuccess(false);
            response.setErrmsg("Failed");
        }
        return response;
    }

    @PostMapping("/update")
    private GenericActionResponse<Pg> update(@RequestBody Pg pg) {
        GenericActionResponse<Pg> response = new GenericActionResponse<>(false);

        boolean isAdded = pgDAO.updatePG(pg);

        if (isAdded) {
            int pg_id = pg.getPg_id();
            response.setSuccess(true);
            response.setSuccessmsg(String.valueOf(pg_id));
        } else {
            response.setSuccess(false);
            response.setErrmsg("Failed");
        }
        return response;
    }

    @PostMapping("/updatePgTypeAndDesc")
    private GenericActionResponse<Pg> updatePgTypeAndDesc(
            @RequestParam("pg_id") String pg_id,
            @RequestParam("pg_type") String pg_type,
            @RequestParam("description") String description) {
        GenericActionResponse<Pg> response = new GenericActionResponse<>(false);

        boolean isAdded = pgDAO.updatePgTypeAndDesc(pg_id, pg_type, description);

        if (isAdded) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setErrmsg("Failed");
        }
        return response;
    }

    @PostMapping("/updatePgPrices")
    private GenericActionResponse<Pg> updatePgPrices(
            @RequestParam("pg_id") String pg_id,
            @RequestParam("deposit") String deposit,
            @RequestParam("maintenance") String maintenance,
            @RequestParam("electric_charges") String electric_charges) {
        GenericActionResponse<Pg> response = new GenericActionResponse<>(false);

        if (Objects.equals(maintenance, "") || Objects.equals(maintenance, null)) {
            maintenance = "0";
        }

        boolean isAdded = pgDAO.updatePgPrices(pg_id, deposit, electric_charges, maintenance);

        if (isAdded) {
            response.setSuccess(true);
        } else {
            response.setSuccess(false);
            response.setErrmsg("Failed");
        }
        return response;
    }

    @PostMapping("/getAllByLocation/{location}")
    private List<Pg> getAllByLocation(
            @PathVariable String location,
            @RequestParam("filterAmenities") String filterAmenities,
            @RequestParam("filterPgType") String filterPgType,
            @RequestParam("filterBudget") String filterBudget,
            @RequestParam("filterSharingType") String filterSharingType) {

        JSONArray jsonArrayAmenities = new JSONArray(filterAmenities);
        JSONArray jsonArrayBudget = new JSONArray(filterBudget);

        JSONObject jsonObjectAmenities = jsonArrayAmenities.getJSONObject(0);
        JSONObject jsonObjectBudget = jsonArrayBudget.getJSONObject(0);

        Amenities amenities =
                new Amenities(
                        jsonObjectAmenities.getString("wifi"),
                        jsonObjectAmenities.getString("food"),
                        jsonObjectAmenities.getString("power_backup"),
                        jsonObjectAmenities.getString("parking"),
                        jsonObjectAmenities.getString("attached_washroom"),
                        jsonObjectAmenities.getString("air_conditioner"),
                        jsonObjectAmenities.getString("washing_machine"));

        int minValue = jsonObjectBudget.getInt("min");
        int maxValue = jsonObjectBudget.getInt("max");

        return pgDAO.getAllPGByLocation(
                location, amenities, filterPgType, minValue, maxValue, filterSharingType);
    }

    @PostMapping("/getAllLocations")
    private List<String> getAllLocations(@RequestParam("location") String location) {
        return pgDAO.getAllLocations(location);
    }

    @PostMapping("/getByPgId")
    public List<Pg> getByPgId(@RequestParam("pg_id") String pg_id) {
        return pgDAO.getByPgId(pg_id);
    }

    @PostMapping("/updateContactPersonDetails")
    public GenericActionResponse<Pg> updateContactPersonDetails(
            @RequestParam("pg_id") String pg_id,
            @RequestParam("contact_person_name") String contact_person_name,
            @RequestParam("contact_person_phone_number") String contact_person_phone_number) {
        GenericActionResponse<Pg> response = new GenericActionResponse<>(false);

        boolean isAdded =
                pgDAO.addContactPersonDetails(
                        pg_id, contact_person_name, contact_person_phone_number);

        response.setSuccess(isAdded);
        return response;
    }

    @PostMapping("/getTotalCountByLocation/{location}")
    public GenericActionResponse<Pg> getTotalCountByLocation(
            @PathVariable String location,
            @RequestParam("filterAmenities") String filterAmenities,
            @RequestParam("filterPgType") String filterPgType,
            @RequestParam("filterBudget") String filterBudget,
            @RequestParam("filterSharingType") String filterSharingType) {
        GenericActionResponse<Pg> response = new GenericActionResponse<>(false);

        JSONArray jsonArrayAmenities = new JSONArray(filterAmenities);
        JSONArray jsonArrayBudget = new JSONArray(filterBudget);

        JSONObject jsonObjectAmenities = jsonArrayAmenities.getJSONObject(0);
        JSONObject jsonObjectBudget = jsonArrayBudget.getJSONObject(0);

        Amenities amenities =
                new Amenities(
                        jsonObjectAmenities.getString("wifi"),
                        jsonObjectAmenities.getString("food"),
                        jsonObjectAmenities.getString("power_backup"),
                        jsonObjectAmenities.getString("parking"),
                        jsonObjectAmenities.getString("attached_washroom"),
                        jsonObjectAmenities.getString("air_conditioner"),
                        jsonObjectAmenities.getString("washing_machine"));

        int minValue = jsonObjectBudget.getInt("min");
        int maxValue = jsonObjectBudget.getInt("max");

        long totalCount =
                pgDAO.getTotalCountByLocation(
                        location, amenities, filterPgType, minValue, maxValue, filterSharingType);

        response.setSuccessmsg(String.valueOf(totalCount));
        return response;
    }

    @PostMapping("/getAllBySimilarity")
    public List<Pg> getAllBySimilarity(
            @RequestParam("pg_type") String pg_type, @RequestParam("location") String location) {
        return pgDAO.getAllBySimilarity(pg_type, location);
    }

    @PostMapping("/getAllByOwner")
    public List<Pg> getAllByOwner(@RequestParam("owner_id") String owner_id) {
        return pgDAO.getAllByOwnerId(owner_id);
    }
}
