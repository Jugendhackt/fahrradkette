package org.jugendhackt.fahrradkette;

import java.math.BigInteger;

public class Bike {

    public double longitude;
    public double latitude;
    public long price;
    public int code;
    public String name;
    public String specialities;

    public Bike(double longitude, double latitude, long price, int code, String name, String specialities) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.code = code;
        this.name = name;
        this.specialities = specialities;
    }

    public BigInteger getLongitude() {
        long rounded = Math.round(this.longitude * 100000);
        return BigInteger.valueOf(rounded);
    }

    public BigInteger getLatitude() {
        long rounded = Math.round(this.latitude * 100000);
        return BigInteger.valueOf(rounded);
    }

    public BigInteger getCode() {
        return BigInteger.valueOf(code);
    }

    public BigInteger getPrice() {
        return BigInteger.valueOf(price);
    }
}
