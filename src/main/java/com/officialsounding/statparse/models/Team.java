package com.officialsounding.statparse.models;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class Team {
    private final List<Jam> jams;

    public List<Jam> getJams() {
        return jams;
    }

    public Team(List<Jam> jams) {

        this.jams = jams;
    }
}
