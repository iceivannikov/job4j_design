package ru.job4j.serialization.hwjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Product product = new Product(true, 150.0, "Telephone",
                new Manufacturer("Apple", "USA"),
                new String[] {"electronics", "sale"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(product));

        final String productJson =
                "{"
                        + "\"inStock\":false,"
                        + "\"price\":200.0,"
                        + "\"name\":TV,"
                        + "\"manufacturer\":"
                        + "{"
                        + "\"name\":\"LG\","
                        + "\"country\":\"Korea\""
                        + "},"
                        + "\"tags\":"
                        + "[\"electronics\",\"sale\"]"
                        + "}";

        final Product productMod = gson.fromJson(productJson, Product.class);
        System.out.println(productMod);
    }
}
