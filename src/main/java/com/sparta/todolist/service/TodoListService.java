package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoListResponseDto createTodo(TodoListRequestDto requestDto) {
        TodoList todo = new TodoList(requestDto);
        TodoList saveTodo = todoListRepository.save(todo);

        TodoListResponseDto todoListResponseDto = new TodoListResponseDto(todo);

        return todoListResponseDto;
    }

    public List<TodoListResponseDto> getTodos() {
        return todoListRepository.findAllByOrderByCreatedAtDesc().stream().map(TodoListResponseDto::new).toList();
    }

    public TodoListResponseDto getTodoById(Long id) {
        TodoList todo = todoListRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );

        return new TodoListResponseDto(todo);
    }
}
