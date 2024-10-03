package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, URISyntaxException {
        ClassLoader classLoader = ConnectionDemo.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("app.properties")).toURI());
        Config config = new Config(file.getPath());
        config.load();
        String driver = config.value("driver");
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        Class.forName(driver);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
