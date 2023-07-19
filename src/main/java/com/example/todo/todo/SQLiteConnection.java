package com.example.todo.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private static final String DB_URL = "jdbc:sqlite:src/main/java/com/example/todo/todo/ToDo.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}