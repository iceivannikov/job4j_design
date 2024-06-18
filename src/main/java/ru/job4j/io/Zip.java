package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final ArgsName argsName;

    public Zip(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("The number of elements must be three");
        }
        argsName = ArgsName.of(args);
        checkArgs(argsName);
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                File fileToZip = source.toFile();
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileToZip))) {
                    zip.putNextEntry(new ZipEntry(source.toString()));
                    zip.write(input.readAllBytes());
                    zip.closeEntry();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Path> getSources() throws IOException {
        Path source = Paths.get(argsName.get("d"));
        return Search.search(source, path -> !path.toFile().getName().endsWith(argsName.get("e")));
    }

    public File getTarget() {
        return Paths.get(argsName.get("o")).toFile();
    }

    private void checkArgs(ArgsName argsName) {
        File file = Paths.get(argsName.get("d")).toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException("File " + file + " does not exist");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("The file extension must start with '.'");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The extension must not contain .zip");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip(args);
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        List<Path> sources = zip.getSources();
        File target = zip.getTarget();
        zip.packFiles(sources, target);
    }
}
