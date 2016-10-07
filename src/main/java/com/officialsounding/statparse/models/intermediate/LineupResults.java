package com.officialsounding.statparse.models.intermediate;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class LineupResults {
    private final List<LineupEntry> home;
    private final List<LineupEntry> away;

    public LineupResults(List<LineupEntry> home, List<LineupEntry> away) {
        this.home = home;
        this.away = away;
    }

    public List<LineupEntry> getHome() {
        return home;
    }

    public List<LineupEntry> getAway() {
        return away;
    }
}
