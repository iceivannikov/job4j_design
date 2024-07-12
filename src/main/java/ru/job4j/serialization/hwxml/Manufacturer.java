package ru.job4j.serialization.hwxml;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "manufacturer")
public class Manufacturer {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String country;

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Manufacturer() {
    }

    @Override
    public String toString() {
        return "Manufacturer{"
                + "name='" + name + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
