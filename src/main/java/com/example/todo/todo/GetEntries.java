package com.example.todo.todo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GetEntries {
    public static List<ToDo> getAllEntries() throws SQLException {
        List<ToDo> entries = new ArrayList<>();

        Connection connection = SQLiteConnection.getConnection();

        String selectSQL = "SELECT * FROM Tasks";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String description = resultSet.getString("description");
            String creationDate = resultSet.getString("creationDate");
            String completionDate = resultSet.getString("completionDate");

            LocalDateTime creationDateTime = LocalDateTime.parse(creationDate, formatter);

            LocalDateTime completionDateTime = null;
            if (completionDate != null) {
                completionDateTime = LocalDateTime.parse(completionDate, formatter);
            }

            ToDo todo = new ToDo(id, description, creationDate, completionDate);
            entries.add(todo);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return entries;
    }
}