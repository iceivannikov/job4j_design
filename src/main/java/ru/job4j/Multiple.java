package ru.job4j;

import java.io.FileOutputStream;
import java.io.IOException;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 2; i < 10; i++) {
                fos.write(("1 * " + i + " = " + i).getBytes());
                fos.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
