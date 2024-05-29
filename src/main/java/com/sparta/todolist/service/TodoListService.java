package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.exception.InvalidPasswordException;
import com.sparta.todolist.repository.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public TodoListResponseDto createTodo(TodoListRequestDto requestDto, User user) {
        TodoList todo = new TodoList(requestDto, user);
        TodoList saveTodo = todoListRepository.save(todo);

        TodoListResponseDto todoListResponseDto = new TodoListResponseDto(todo);

        return todoListResponseDto;
    }

    public List<TodoListResponseDto> getTodos() {
        List<TodoList> todoLists;
        todoLists = todoListRepository.findAllByOrderByCreatedAtDesc();
        return todoLists.stream()
                .map(TodoListResponseDto::new)
                .collect(Collectors.toList());
    }

    public TodoListResponseDto getTodoById(Long id) {
        return new TodoListResponseDto(findTodoList(id));
    }

    @Transactional
    public Long updateTodo(Long id, String password, TodoListRequestDto requestDto, User user) {
        TodoList todo = findTodoList(id, user);
        if (todo.getPassword().equals(password)) {
            todo.update(requestDto, user);
        } else {
            throw new InvalidPasswordException();
        }

        return id;

    }

    public Long deleteTodo(Long id, String password, User user) {
        TodoList todo = findTodoList(id, user);
        if (todo.getPassword().equals(password)) {
            todoListRepository.delete(todo);
        } else {
            throw new InvalidPasswordException();
        }

        return id;
    }

    //삭제, 수정에 필요한 findTodoList
    private TodoList findTodoList(Long id, User user) {
        todoListRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
        return todoListRepository.findByIdAndUser(id, user).orElseThrow(() ->
                new IllegalArgumentException("본인이 작성한 일정만 작업이 가능합니다."));
    }

    //조회는 id값만 일치하면 모두 조회되어야함.
    private TodoList findTodoList(Long id) {
        return todoListRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }
}
