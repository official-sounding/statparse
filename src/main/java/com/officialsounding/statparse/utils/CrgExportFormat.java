package com.officialsounding.statparse.utils;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter on 4/21/2017.
 */
@XmlRootElement(name="document")
public class CrgExportFormat {


    public CrgExportFormat(){
        teams = new ArrayList<CrgExportTeam>();
    }
    @XmlAttribute(name="Version")
    public String getVersion(){ return "3.9.0"; }

    @XmlElementWrapper(name="Teams")
    @XmlElement(name="Team")
    public List<CrgExportTeam> getTeams() { return teams; }


    private List<CrgExportTeam> teams;
}


