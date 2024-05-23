package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream("data/input.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
