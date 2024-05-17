package com.sparta.todolist.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoListRequestDto {
    private String username;
    private String title;
    private String contents;
    private String password;
}
