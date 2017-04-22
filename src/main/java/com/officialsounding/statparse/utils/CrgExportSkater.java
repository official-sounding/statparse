package com.officialsounding.statparse.utils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Skater")
public class CrgExportSkater {

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @XmlAttribute(name="Id")
    public String getId() { return name; }

    @XmlElement(name="Name")
    public String getName() { return name; }
    @XmlElement(name="Number")

    public String getNumber() { return number; }

    private String name;
    private String number;


}
