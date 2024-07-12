package ru.job4j.serialization.json.hwjson;

public class Manufacturer {
    private final String name;
    private final String country;

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer{"
                + "name='" + name + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
