package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/result.txt")) {
            output.write("Hello, world!".getBytes());
            output.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
