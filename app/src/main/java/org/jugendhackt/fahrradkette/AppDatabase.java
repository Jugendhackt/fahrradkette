package org.jugendhackt.fahrradkette;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {BikeDb.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BikeDao bikeDao();
}