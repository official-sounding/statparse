package com.officialsounding.statparse.models;

/**
 * Created by Peter on 8/23/2016.
 */
public class Game {
    private final Team home;
    private final Team away;

    public Game(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }
}
