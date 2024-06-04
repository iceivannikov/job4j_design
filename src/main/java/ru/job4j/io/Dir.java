package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("/Users/viktor/IdeaProjects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                for (File listFile : subfile.listFiles()) {
                    System.out.printf("File name: %s, file size: %d%n", listFile.getName(), listFile.length());
                }
            }
            System.out.printf("File name: %s, file size: %d%n", subfile.getName(), subfile.length());
        }
    }
}
