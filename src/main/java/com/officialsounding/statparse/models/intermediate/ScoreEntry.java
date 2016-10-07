package com.officialsounding.statparse.models.intermediate;

import com.officialsounding.statparse.models.Pass;

import java.util.List;

/**
 * Created by Peter on 9/3/2016.
 */
public class ScoreEntry {
    private final int period;
    private final int jam;

    private final boolean lostLead;
    private final boolean leadJammer;
    private final boolean call;
    private final boolean injury;
    private final boolean noPass;

    private final List<Pass> passes;

    public ScoreEntry(int period, int jam, boolean lostLead, boolean leadJammer, boolean call, boolean injury, boolean noPass, List<Pass> passes) {
        this.period = period;
        this.jam = jam;
        this.lostLead = lostLead;
        this.leadJammer = leadJammer;
        this.call = call;
        this.injury = injury;
        this.noPass = noPass;
        this.passes = passes;
    }

    public int getPeriod() {
        return period;
    }

    public int getJam() {
        return jam;
    }

    public boolean isLostLead() {
        return lostLead;
    }

    public boolean isLeadJammer() {
        return leadJammer;
    }

    public boolean isCall() {
        return call;
    }

    public boolean isInjury() {
        return injury;
    }

    public boolean isNoPass() {
        return noPass;
    }

    public List<Pass> getPasses() {
        return passes;
    }
}
