package com.faith.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection() {
        String driveRoot = Paths.get(System.getProperty("user.dir")).getRoot().toString();
        String configPath = Paths.get(driveRoot, "app_resource", "starter_db-config.properties").toString();

        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream(configPath)) {
            props.load(input);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            return DriverManager.getConnection(url, user, password);

        } catch (IOException | SQLException e) {
            String errorMessage = e instanceof IOException ? "❌ Failed to load database properties file" : "❌ Failed to connect to the database";
            throw new RuntimeException(errorMessage, e);
        }

    }
}