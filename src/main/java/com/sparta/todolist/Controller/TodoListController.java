package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoListController {
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping
    public TodoListResponseDto createTodo(@RequestBody TodoListRequestDto requestDto){
        return todoListService.createTodo(requestDto);
    }
    @GetMapping
    public List<TodoListResponseDto> getTodos() {
        return todoListService.getTodos();
    }

    @GetMapping("/{id}")
    public TodoListResponseDto getTodoById(@PathVariable Long id) {
        return todoListService.getTodoById(id);
    }
    @PutMapping("/{id}/{password}")
    public Long updateTodo(@PathVariable Long id, @PathVariable String password, @RequestBody TodoListRequestDto requestDto) {
        return todoListService.updateTodo(id,password,requestDto);
    }

    @DeleteMapping("/{id}/{password}")
    public Long deleteTodo(@PathVariable Long id, @PathVariable String password) {
        return todoListService.deleteTodo(id,password);
    }
}
