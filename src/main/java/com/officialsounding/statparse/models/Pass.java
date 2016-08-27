package com.officialsounding.statparse.models;

/**
 * Created by Peter on 8/23/2016.
 */
public class Pass {
    private final int points;
    private final boolean afterStarPass;

    public Pass(int points, boolean afterStarPass) {
        this.points = points;
        this.afterStarPass = afterStarPass;
    }

    public int getPoints() {
        return points;
    }

    public boolean isAfterStarPass() {
        return afterStarPass;
    }
}
