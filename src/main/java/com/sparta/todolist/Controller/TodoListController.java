package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.service.TodoListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoListController {
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/todo")
    public TodoListResponseDto createTodo(@RequestBody TodoListRequestDto requestDto){
        return todoListService.createTodo(requestDto);
    }
    @GetMapping("/todo")
    public List<TodoListResponseDto> getTodos() {
        return todoListService.getTodos();
    }

}
