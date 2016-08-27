package com.officialsounding.statparse.models;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class Participant {
    private final String number;
    private final List<BoxTrip> trips;
    private final PlayerType type;

    public Participant(String number, PlayerType type, List<BoxTrip> trips) {
        this.number = number;
        this.trips = trips;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

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
