package com.example.todo.todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEntry {
    public static void delete(int entryId) throws SQLException {
        // Establish a connection to the SQLite database
        Connection connection = SQLiteConnection.getConnection();

        // Prepare the DELETE statement
        String deleteSQL = "DELETE FROM Tasks WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(deleteSQL);
        statement.setInt(1, entryId);

        // Execute the DELETE statement
        statement.executeUpdate();

        // Close the statement and connection
        statement.close();
        connection.close();
    }
}
