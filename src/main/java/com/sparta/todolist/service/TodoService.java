package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.exception.InvalidPasswordException;
import com.sparta.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto, User user) {
        Todo todo = new Todo(requestDto, user);
        Todo saveTodo = todoRepository.save(todo);

        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);

        return todoResponseDto;
    }

    public List<TodoResponseDto> getTodos() {
        List<Todo> todos;
        todos = todoRepository.findAllByOrderByCreatedAtDesc();
        return todos.stream()
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    public TodoResponseDto getTodoById(Long id) {
        return new TodoResponseDto(findTodoList(id));
    }

    @Transactional
    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = findTodoList(id, user);
        if (todo.getPassword().equals(requestDto.getPassword())) {
            todo.update(requestDto, user);
        } else {
            throw new InvalidPasswordException();
        }
        return new TodoResponseDto(todo);
    }

    public void deleteTodo(Long id, TodoRequestDto requestDto, User user) {
        Todo todo = findTodoList(id, user);
        if (todo.getPassword().equals(requestDto.getPassword())) {
            todoRepository.delete(todo);
        } else {
            throw new InvalidPasswordException();
        }
    }

    //삭제, 수정에 필요한 findTodoList
    private Todo findTodoList(Long id, User user) {
        todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
        return todoRepository.findByIdAndUser(id, user).orElseThrow(() ->
                new IllegalArgumentException("본인이 작성한 일정만 작업이 가능합니다."));
    }

    //조회는 id값만 일치하면 모두 조회되어야함.
    protected Todo findTodoList(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }
}
