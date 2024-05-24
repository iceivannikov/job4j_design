package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("data/even.txt")) {
            StringBuilder numbers = new StringBuilder();
            int read;
            while ((read = fis.read()) != -1) {
                numbers.append((char) read);
            }
            String[] numbersArray = numbers.toString().split("\n");
            for (String string : numbersArray) {
                int number = Integer.parseInt(string);
                System.out.println(number + " is even: " + (number % 2 == 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
