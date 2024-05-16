package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.repository.TodoListRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new TodoListResponseDto(findTodoList(id));
    }
    @Transactional
    public Long updateTodo(Long id, String password, TodoListRequestDto requestDto) {
        //해당 메모 있는지 확인
        TodoList todo = findTodoList(id);
        // memo 내용 수정
        if(todo.getPassword().equals(password)) {
            todo.update(requestDto);
        }else{
            throw new IllegalArgumentException("비밀번호가 옳지 않습니다.");
        }

        return id;

    }
    private TodoList findTodoList(Long id){
        // 해당 메모가 DB에 존재하는지 확인
        return todoListRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }
}
