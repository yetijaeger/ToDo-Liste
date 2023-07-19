package com.example.todo;

import com.example.todo.todo.DeleteEntry;
import com.example.todo.todo.GetEntries;
import com.example.todo.todo.ToDo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HelloApplication extends Application
{
    public static void main(String[] args) throws SQLException {
        launch();
    }

    @Override

    public void start(Stage stage) throws SQLException {

        // Retrieve data from the database
        List<ToDo> entries = GetEntries.getAllEntries();
        ObservableList<ToDo> data = FXCollections.observableArrayList(entries);
        
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

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create a ScrollPane and set the TableView as its content
        ScrollPane scrollPane = new ScrollPane(tableView);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        // Create a scene and add the TableView to it
        Scene scene = new Scene(scrollPane);

        // Set the scene to the stage and show the stage
        stage.setScene(scene);
        stage.show();

        // Create the delete button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            ToDo selectedTodo = tableView.getSelectionModel().getSelectedItem();
            if (selectedTodo != null) {
                data.remove(selectedTodo);
                // Delete the entry from the database
                try {
                    DeleteEntry.delete(selectedTodo.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create a VBox to hold the table and the delete button
        VBox vbox = new VBox(tableView, deleteButton);

        // Create a ScrollPane and set the VBox as its content
        ScrollPane scrollPane2 = new ScrollPane(vbox);
        scrollPane2.setFitToHeight(true);
        scrollPane2.setFitToWidth(true);

        // Create a scene and add the ScrollPane to it
        Scene scene2 = new Scene(scrollPane2);

        // Set the scene to the stage and show the stage
        stage.setScene(scene2);
        stage.show();
    }
}