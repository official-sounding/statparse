package com.officialsounding.statparse.models;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class Team {

    private final List<Skater> roster;
    private final String league;
    private final String team;
    private final String color;

    public Team(String league, String team, String color, List<Skater> roster) {
        this.roster = roster;
        this.league = league;
        this.team = team;
        this.color = color;
    }

    public List<Skater> getRoster() {
        return roster;
    }

    public String getLeague() {
        return league;
    }

    public String getTeam() {
        return team;
    }

    public String getColor() {
        return color;
    }
}
