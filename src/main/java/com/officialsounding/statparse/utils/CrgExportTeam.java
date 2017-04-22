package com.officialsounding.statparse.utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="Team")
public class CrgExportTeam {


    public CrgExportTeam() {
        this.skaters = new ArrayList<CrgExportSkater>();
    }

    public void setName(String name) {
        this.name = name;
    }


    @XmlAttribute(name="Id")
    public String getId() { return name; }

    @XmlElement(name="Name")
    public String getName() { return name; }

    @XmlElement(name="Skater")
    public List<CrgExportSkater> getSkaters() {
        return skaters;
    }

    private String name;
    private List<CrgExportSkater> skaters;
}
