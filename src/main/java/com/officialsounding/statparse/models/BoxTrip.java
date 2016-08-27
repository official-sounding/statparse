package com.officialsounding.statparse.models;

/**
 * Created by Peter on 8/23/2016.
 */
public enum BoxTrip {
    Full,
    Started,
    Continued,
    SatBetween,
    SatBetweenAndFinished,
    Injured,
    Unknown;


    public static BoxTrip fromLineup(String lineupSymbol) {
        if(lineupSymbol == null) {
            return Unknown;
        }

        switch(lineupSymbol.toUpperCase()) {
            case "/":
               return Started;
            case "|":
               return Continued;
            case "X":
                return Full;
            case "S":
                return SatBetween;
            case "$":
                return SatBetweenAndFinished;
            case "3":
                return Injured;
            default:
                return Unknown;
        }
    }
}
