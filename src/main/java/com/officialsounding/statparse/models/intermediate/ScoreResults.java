package com.officialsounding.statparse.models.intermediate;

import java.util.List;

/**
 * Created by Peter on 9/3/2016.
 */
public class ScoreResults {

    public final List<ScoreEntry> homeJams;
    private final List<ScoreEntry> awayJams;

    public ScoreResults(List<ScoreEntry> homeJams, List<ScoreEntry> awayJams) {
        this.homeJams = homeJams;
        this.awayJams = awayJams;
    }

    public List<ScoreEntry> getHomeJams() {
        return homeJams;
    }

    public List<ScoreEntry> getAwayJams() {
        return awayJams;
    }
}
