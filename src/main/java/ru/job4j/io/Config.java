package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.trim().length() == 1 && line.trim().charAt(0) == '=') {
                    throw new IllegalArgumentException("Line contains only the = sign");
                }
                if (line.length() > 2 && !line.trim().contains("=") && !line.trim().startsWith("#")) {
                    throw new IllegalArgumentException("line does not contain an =");
                }
                if (!line.isEmpty() && !line.trim().startsWith("#")) {
                    int i = line.indexOf("=");
                    String key = line.substring(0, i);
                    String value = line.substring(i + 1);
                    if (key.isEmpty() || value.isEmpty()) {
                        throw new IllegalArgumentException(
                                "line begins with an = sign without a key, "
                                        + "or ends with an = sign without a value");
                    }
                    values.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        System.out.println(new Config("data/pair_without_comment.properties"));
        System.out.println(new Config("data/with_comments.properties"));
    }
}