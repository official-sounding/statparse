package com.officialsounding.statparse.models;

import java.util.List;

/**
 * Created by Peter on 8/23/2016.
 */
public class Jam {
    private int period;
    private int jamNumber;

    private final boolean starpass;

    private final boolean called;
    private final boolean lost;
    private final boolean injury;
    private final boolean lead;

    private final List<Participant> participants;
    private List<Pass> passes;

    public Jam(int period, int jamNumber, boolean starpass, boolean called, boolean lost, boolean injury, boolean lead, List<Participant> participants, List<Pass> passes) {
        this.period = period;
        this.jamNumber =jamNumber;
        this.starpass = starpass;
        this.called = called;
        this.lost = lost;
        this.injury = injury;
        this.lead = lead;
        this.participants = participants;
        this.passes = passes;
    }

    public int getPeriod() {
        return period;
    }

    public int getJamNumber() {
        return jamNumber;
    }

    public boolean isStarpass() {
        return starpass;
    }

    public boolean isCalled() {
        return called;
    }

    public boolean isLost() {
        return lost;
    }

    public boolean isInjury() {
        return injury;
    }

    public boolean isLead() {
        return lead;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<Pass> getPasses() {
        return passes;
    }
}
