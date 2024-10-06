package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s (%s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name TEXT"
        );
        execute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD %s %s;",
                tableName, columnName, type
        );
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        );
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream is = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (is == null) {
                throw new RuntimeException("Файл конфигурации 'app.properties' не найден.");
            }
            config.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки файла конфигурации", e);
        }
        try (TableEditor editor = new TableEditor(config)) {
            String tableName = "test_table";
            editor.createTable(tableName);
            System.out.println(editor.getTableScheme(tableName));
            String columnName = "age";
            editor.addColumn(tableName, columnName, "INT");
            System.out.println(editor.getTableScheme(tableName));
            String newColumName = "year_of_birth";
            editor.renameColumn(tableName, columnName, newColumName);
            System.out.println(editor.getTableScheme(tableName));
            editor.dropColumn(tableName, newColumName);
            System.out.println(editor.getTableScheme(tableName));
            editor.dropTable(tableName);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка работы с TableEditor", e);
        }
    }
}
