package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.service.TodoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoListController {
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @Tag(name = "1. 일정생성")
    @Operation(summary = "createTodo", description = "일정 제목, 내용, 이름, 비밀번호 입력시 생성완료")
    @PostMapping
    public TodoListResponseDto createTodo(@Valid @RequestBody TodoListRequestDto requestDto){
        return todoListService.createTodo(requestDto);
    }

    @Tag(name = "2. 전체일정조회")
    @Operation(summary = "getTodos", description = "조건없이 전체일정 조회")
    @GetMapping
    public List<TodoListResponseDto> getTodos() {
        return todoListService.getTodos();
    }

    @Tag(name = "3. 선택일정조회")
    @Operation(summary = "getTodoById", description = "파라미터에 id값 제시하면 해당 일정 조회")
    @GetMapping("/{id}")
    public TodoListResponseDto getTodoById(@PathVariable Long id) {
        return todoListService.getTodoById(id);
    }

    @Tag(name = "4. 일정수정")
    @Operation(summary = "updateTodo", description = "비밀번호 일치하면 해당 id값의 일정을 찾아 제목, 내용, 담당자 수정 가능")
    @PutMapping("/{id}/{password}")
    public Long updateTodo(@PathVariable Long id, @PathVariable String password, @Valid @RequestBody TodoListRequestDto requestDto) {
        return todoListService.updateTodo(id,password,requestDto);
    }

    @Tag(name = "5. 일정삭제")
    @Operation(summary = "deleteTodo", description = "비밀번호 일치하면 해당 id값의 일정을 찾아 삭제")
    @DeleteMapping("/{id}/{password}")
    public Long deleteTodo(@PathVariable Long id, @PathVariable String password) {
        return todoListService.deleteTodo(id,password);
    }
}
