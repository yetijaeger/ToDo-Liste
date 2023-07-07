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

import java.time.LocalDateTime;

public class HelloApplication extends Application
{
    @Override
    public void start(Stage stage)
    {
        // Data
        ObservableList<ToDo> data = FXCollections.observableArrayList(
                new ToDo(1, "Schnitzel kaufen", LocalDateTime.now(), LocalDateTime.now()),
                new ToDo(2, "Salami kaufen", LocalDateTime.now(), null),
                new ToDo(3, "Yo mama", LocalDateTime.now(), null),
                new ToDo(4, "Yolo", LocalDateTime.now(), null),
                new ToDo(5, "Sport", LocalDateTime.now(), null)
        );

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

    public static void main(String[] args)
    {
        launch();
    }
}