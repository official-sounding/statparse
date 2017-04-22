package com.officialsounding.statparse.models;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class Skater {
    private final String number;
    private final String name;
    private final List<BoxTrip> trips;
    private final PlayerType type;

    public Skater(String number, String name) {
        this.number = number;
        this.trips = null;
        this.type = null;
        this.name = name;
    }

    public Skater(String number, PlayerType type, List<BoxTrip> trips) {
        this.number = number;
        this.trips = trips;
        this.type = type;
        this.name = null;
    }

    public String getNumber() {
        return number;
    }

    public String getName() { return name; }

    public List<BoxTrip> getTrips() {
        return trips;
    }

    public PlayerType getType() {
        return type;
    }

    public boolean isInjured() {
        return trips != null && trips.contains(BoxTrip.Injured);
    }
}
