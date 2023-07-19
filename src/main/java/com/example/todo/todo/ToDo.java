package com.example.todo.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ToDo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    int id;
    String description;
    LocalDateTime creationDate;
    LocalDateTime finishDate;

    public ToDo(int id, String description, String creationDate, String finishDate)
    {
        this.id = id;
        this.description = description;
        this.creationDate = LocalDateTime.parse(creationDate, formatter);

        if (finishDate != null && !finishDate.isEmpty()) {
            this.finishDate = LocalDateTime.parse(finishDate, formatter);
        }
    }

    public int getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}