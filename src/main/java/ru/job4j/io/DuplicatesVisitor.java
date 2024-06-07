package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        duplicates.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : duplicates.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println(entry.getKey());
                entry.getValue().forEach(System.out::println);
            }
        }
    }
}
