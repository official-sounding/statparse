package com.officialsounding.statparse.models.intermediate;

import com.officialsounding.statparse.models.Team;

/**
 * Created by Peter on 4/21/2017.
 */
public class IGRF {
    private final Team home;
    private final Team away;

    public IGRF(Team home, Team away) {
        this.home = home;
        this.away = away;
    }

    public Team getHome() { return home; }

    public Team getAway() {
        return away;
    }
}
