package ru.job4j.serialization.hwjson;

import java.util.Arrays;

public class Product {
    private final boolean inStock;
    private final double price;
    private final String name;
    private final Manufacturer manufacturer;
    private final String[] tags;

    public Product(boolean inStock, double price, String name, Manufacturer manufacturer, String[] tags) {
        this.inStock = inStock;
        this.price = price;
        this.name = name;
        this.manufacturer = manufacturer;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Product{"
                + "inStock=" + inStock
                + ", price=" + price
                + ", name='" + name + '\''
                + ", manufacturer=" + manufacturer
                + ", tags=" + Arrays.toString(tags)
                + '}';
    }
}
