package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.TodoListRequestDto;
import com.sparta.todolist.dto.TodoListResponseDto;
import com.sparta.todolist.security.UserDetailsImpl;
import com.sparta.todolist.service.TodoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "TodoListController", description = "일정 관련 api")
@RequestMapping("api/todos")
public class TodoListController {
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @Operation(summary = "일정 생성", description = "일정 제목, 내용, 이름, 비밀번호 입력시 생성완료")
    @PostMapping
    public TodoListResponseDto createTodo(@Valid @RequestBody TodoListRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return todoListService.createTodo(requestDto,userDetails.getUser());
    }


    @Operation(summary = "전체 일정 조회", description = "조건없이 전체일정 조회")
    @GetMapping
    public List<TodoListResponseDto> getTodos() {
        return todoListService.getTodos();
    }


    @Operation(summary = "선택 일정 조회", description = "파라미터에 id값 제시하면 해당 일정 조회")
    @GetMapping("/{id}")
    public TodoListResponseDto getTodoById(@PathVariable Long id) {
        return todoListService.getTodoById(id);
    }

    @Operation(summary = "일정 수정", description = "비밀번호 일치하면 해당 id값의 일정을 찾아 제목, 내용, 담당자 수정 가능")
    @PutMapping("/{id}/{password}")
    public Long updateTodo(@PathVariable Long id, @PathVariable String password, @RequestBody TodoListRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoListService.updateTodo(id,password,requestDto,userDetails.getUser());
    }

    @Operation(summary = "일정 삭제", description = "비밀번호 일치하면 해당 id값의 일정을 찾아 삭제")
    @DeleteMapping("/{id}/{password}")
    public Long deleteTodo(@PathVariable Long id, @PathVariable String password, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoListService.deleteTodo(id,password,userDetails.getUser());
    }
}
