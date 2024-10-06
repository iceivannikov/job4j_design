package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties config;
    private final String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users;
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            List<String> lines = reader.lines().toList();
            if (lines.size() != 2) {
                throw new IllegalArgumentException("The number of elements is not equal to two");
            }
            users = lines.stream()
                    .filter(this::isValid)
                    .map(this::createUser)
                    .toList();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name());
                    preparedStatement.setString(2, user.email());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    private boolean isValid(String line) {
        String[] split = line.split(";");
        String namePart = split[0].trim();
        String emailPart = split[1].trim();
        if (split.length != 2) {
            throw new IllegalArgumentException("The line must consist of two parts");
        }
        if (!namePart.isEmpty() && emailPart.contains("@")) {
            return true;
        } else {
            throw new IllegalArgumentException("The string must match the pattern name;email");
        }
    }

    private User createUser(String line) {
        String[] split = line.split(";");
        String name = split[0].trim();
        String email = split[1].trim();
        return new User(name, email);
    }

    private record User(String name, String email) {
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "data/dump.txt");
        dataBase.save(dataBase.load());
    }
}
