package com.officialsounding.statparse.models;

/**
 * Created by Peter on 8/23/2016.
 */
public class Penalty {
    private final String code;
    private final int jamAssessed;
    private final int jamSat;

    public String getCode() {
        return code;
    }

    public int getJamAssessed() {
        return jamAssessed;
    }

    public int getJamSat() {
        return jamSat;
    }

    public Penalty(String code, int jamAssessed, int jamSat) {

        this.code = code;
        this.jamAssessed = jamAssessed;
        this.jamSat = jamSat;
    }
}
