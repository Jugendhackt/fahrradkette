package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BikeDao {
    @Query("SELECT * FROM BikeDb")
    List<BikeDb> getAll();

    @Query("SELECT * FROM BikeDb WHERE uid IN (:userIds)")
    List<BikeDb> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM BikeDb WHERE name LIKE :first AND "
            + "description LIKE :last LIMIT 1")
    BikeDb findByName(String first, String last);

    @Insert
    void insert (BikeDb bikeDb);

    @Delete
    void delete(BikeDb user);
}