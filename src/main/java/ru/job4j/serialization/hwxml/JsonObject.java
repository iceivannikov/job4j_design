package ru.job4j.serialization.hwxml;

import org.json.JSONObject;

public class JsonObject {
    public static void main(String[] args) {
        Car car = new Car(true, 79999.99, "Model S",
                new Manufacturer("Tesla", "USA"),
                new String[]{"Autopilot", "Electric", "Luxury"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isElectric", car.isElectric());
        jsonObject.put("price", car.getPrice());
        jsonObject.put("model", car.getModel());
        jsonObject.put("manufacturer", car.getManufacturer());
        jsonObject.put("features", car.getFeatures());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(car));
    }
}
