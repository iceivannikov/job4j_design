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
                if (checkLine(line)) {
                    int i = line.indexOf("=");
                    String key = line.substring(0, i);
                    String value = line.substring(i + 1);
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

    private boolean checkLine(String line) {
        boolean result = true;
        line = line.trim();
        int i = line.indexOf("=");
        if (line.isEmpty() || line.startsWith("#") || i == -1) {
            result = false;
        } else {
            String key = line.substring(0, i).trim();
            String value = line.substring(i + 1).trim();
            if (key.isEmpty() || value.isEmpty()) {
                result = false;
            }
        }
        if (!result && !(line.isEmpty() || line.startsWith("#"))) {
            throw new IllegalArgumentException("the entry is not correct");
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        System.out.println(new Config("data/pair_without_comment.properties"));
        System.out.println(new Config("data/with_comments.properties"));
    }
}