package com.sparta.todolist.dto;

import com.sparta.todolist.entity.TodoList;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoListResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TodoListResponseDto(TodoList todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.username = todo.getUsername();
        this.createdAt = todo.getCreatedAt();
        this.modifiedAt = todo.getModifiedAt();
    }
}
