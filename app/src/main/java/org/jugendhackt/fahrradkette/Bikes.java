package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONObject;

@Entity
public class Bikes {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "users")
    private String users;

    @ColumnInfo(name = "owner")
    private String owner;

    @ColumnInfo(name = "lon")
    private double lon;

    @ColumnInfo(name = "lat")
    private double lat;

    @ColumnInfo(name = "description")
    private String description;

    // https://developer.android.com/training/data-storage/room/
    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
