package com.example.todo;

import com.example.todo.todo.ToDo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application
{
    static String dbPath = "jdbc:sqlite:C:/Users/USER/Documents/datenbanken/todo.db"; //
    public static ObservableList<ToDo> data = FXCollections.observableArrayList();

    public static void main(String[] args)
    {
        String sql = "SELECT * FROM tasks";

        try (Connection connection = DriverManager.getConnection(dbPath);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {

            System.out.println(resultSet);

            while (resultSet.next())
            {
                // Create formatter to convert db datetime to LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Get data from row
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                LocalDateTime creationDate = LocalDateTime.parse(resultSet.getString("creationDate"), formatter);
                LocalDateTime completionDate = resultSet.getString("completionDate") == null ? null : LocalDateTime.parse(resultSet.getString("completionDate"), formatter);

                // Create new ToDo Object and add it to data list
                ToDo toDo = new ToDo(id, description, creationDate, completionDate);
                data.add(toDo);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        launch();
    }

    @Override
    public void start(Stage stage)
    {
        // Create table
        TableView<ToDo> tableView = new TableView<>();

        TableColumn<ToDo, Integer> nameColumn = new TableColumn<>("ID");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ToDo, String> descriptionColum = new TableColumn<>("Description");
        descriptionColum.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<ToDo, LocalDateTime> creationColum = new TableColumn<>("Creation");
        creationColum.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        TableColumn<ToDo, LocalDateTime> finishColum = new TableColumn<>("Finish");
        finishColum.setCellValueFactory(new PropertyValueFactory<>("finishDate"));

        // Add columns to the TableView
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(descriptionColum);
        tableView.getColumns().add(creationColum);
        tableView.getColumns().add(finishColum);

        // Set the data to the TableView
        tableView.setItems(data);

        // Create a scene and add the TableView to it
        Scene scene = new Scene(tableView);

        // Set the scene to the stage and show the stage
        stage.setScene(scene);
        stage.show();
    }
}