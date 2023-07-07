package com.example.todo.todo;

import java.time.LocalDateTime;


public class ToDo {
    int id;
    String description;
    LocalDateTime creationDate;
    LocalDateTime finishDate;

    public ToDo(int id, String description, LocalDateTime creationDate, LocalDateTime finishDate)
    {
        this.id = id;
        this.description = description;
        this.creationDate = creationDate;
        this.finishDate = finishDate;
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
