module com.example.todo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.todo to javafx.fxml;
    exports com.example.todo;
    exports com.example.todo.todo;
    opens com.example.todo.todo to javafx.fxml;
}