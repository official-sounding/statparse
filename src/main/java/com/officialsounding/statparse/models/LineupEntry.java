package com.officialsounding.statparse.models;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class LineupEntry {
    private final int period;
    private final int jamNumber;
    private final List<Participant> participants;
    private final boolean hasStarPass;


    public LineupEntry(int period, int jamNumber, List<Participant> participants, boolean hasStarPass) {
        this.period = period;
        this.jamNumber = jamNumber;
        this.participants = participants;
        this.hasStarPass = hasStarPass;
    }

    public int getPeriod() {
        return period;
    }

    public int getJamNumber() {
        return jamNumber;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public boolean isHasStarPass() {
        return hasStarPass;
    }
}
