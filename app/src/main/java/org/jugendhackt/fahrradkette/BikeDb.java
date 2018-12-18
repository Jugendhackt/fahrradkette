package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//import org.json.JSONObject;

@Entity
public class BikeDb {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "lon")
    private double lon;

    @ColumnInfo(name = "lat")
    private double lat;

    @ColumnInfo(name = "isMy")
    private boolean isMy;

    @ColumnInfo(name = "description")
    private String description;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean getIsMy() { return isMy; }

    public void setIsMy(boolean isMy) { this.isMy = isMy; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // https://developer.android.com/training/data-storage/room/
    //die restlichen daten sendet dann der Server bei bedarf einen zu

    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}