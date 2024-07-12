package ru.job4j.serialization.hwjaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.job4j.serialization.hwxml.Car;
import ru.job4j.serialization.hwxml.Manufacturer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(true, 79999.99, "Model S",
                new Manufacturer("Tesla", "USA"),
                new String[]{"Autopilot", "Electric", "Luxury"});

        File file = new File("src/main/java/ru/job4j/serialization/hwjaxb/car.xml");
        String xml;

        try {
            JAXBContext context = JAXBContext.newInstance(Car.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(car, file);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(car, writer);
                xml = writer.getBuffer().toString();
                System.out.println(xml);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                Car result = (Car) unmarshaller.unmarshal(reader);
                System.out.println(result);
            }
            Car unmarshalled = (Car) unmarshaller.unmarshal(file);
            System.out.println(unmarshalled);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
