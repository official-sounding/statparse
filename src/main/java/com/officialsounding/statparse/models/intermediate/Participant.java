package com.officialsounding.statparse.models.intermediate;

import com.officialsounding.statparse.models.BoxTrip;
import com.officialsounding.statparse.models.PlayerType;

import java.util.List;

/**
 * Created by Peter on 4/21/2017.
 */
public class Participant {

    private final String number;
    private final List<BoxTrip> trips;
    private final PlayerType type;

    public String getNumber() {
        return number;
    }

    public List<BoxTrip> getTrips() {
        return trips;
    }

    public PlayerType getType() {
        return type;
    }

    public Participant(String number, PlayerType type, List<BoxTrip> trips) {

        this.number = number;
        this.trips = trips;
        this.type = type;
    }
}
