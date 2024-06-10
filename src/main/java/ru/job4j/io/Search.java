package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        checkArgs(args);
        Path start = Path.of(args[0].trim());
        String extension = args[1].trim();
        search(start, path -> path.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The number of elements must be two");
        }
        Path dir = Path.of(args[0].trim());
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("First argument is not a directory");
        }
        String extension = args[1].trim();
        if (!isValidExtension(extension)) {
            throw new IllegalArgumentException("Second argument is not a valid file extension");
        }
    }

    private static boolean isValidExtension(String extension) {
        return extension.startsWith(".") && extension.length() > 1 && extension.lastIndexOf('.') == 0;
    }
}
