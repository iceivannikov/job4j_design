package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("data/even.txt")) {
            int number;
            boolean isEven;
            while ((number = fis.read()) != -1) {
                    isEven = number % 2 == 0;
                    System.out.println(number + " is even: " + isEven);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
