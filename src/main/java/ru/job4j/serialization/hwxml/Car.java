package ru.job4j.serialization.hwxml;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean isElectric;
    @XmlAttribute
    private double price;
    @XmlAttribute
    private String model;
    @XmlElement
    private Manufacturer manufacturer;
    @XmlElementWrapper(name = "features")
    @XmlElement(name = "feature")
    private String[] features;

    public Car(boolean isElectric, double price, String model, Manufacturer manufacturer, String[] features) {
        this.isElectric = isElectric;
        this.price = price;
        this.model = model;
        this.manufacturer = manufacturer;
        this.features = features;
    }

    public Car() {
    }

    public boolean isElectric() {
        return isElectric;
    }

    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public String[] getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isElectric=" + isElectric
                + ", price=" + price
                + ", model='" + model + '\''
                + ", manufacturer=" + manufacturer
                + ", features=" + Arrays.toString(features)
                + '}';
    }
}
