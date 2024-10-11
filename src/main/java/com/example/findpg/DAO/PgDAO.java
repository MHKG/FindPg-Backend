package com.example.findpg.DAO;

import com.example.findpg.entity.Amenities;
import com.example.findpg.entity.Pg;

import java.util.List;

public interface PgDAO {
    boolean addPG(Pg pg);

    List<Pg> getAllPGByLocation(
            String location,
            Amenities amenities,
            String filterPgType,
            int minValue,
            int maxValue,
            String filterSharingType);

    List<Integer> getPgId(int owner_id, String pg_name, String location);

    List<String> getAllLocations(String location);

    List<Pg> getByPgId(String pg_id);

    boolean updatePgTypeAndDesc(String pg_id, String type, String description);

    boolean updatePgPrices(String pgId, String deposit, String electricCharges, String maintenance);

    boolean addContactPersonDetails(
            String pgId, String contactPersonName, String contactPersonPhoneNumber);

    boolean updatePG(Pg pg);

    long getTotalCountByLocation(
            String location,
            Amenities amenities,
            String filterPgType,
            int minValue,
            int maxValue,
            String filterSharingType);

    List<Pg> getAllBySimilarity(String pgType, String location);

    List<Pg> getAllByOwnerId(String ownerId);
}
