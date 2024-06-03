package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.security.UserDetailsImpl;
import com.sparta.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "TodoListController", description = "일정 관련 api")
@RequestMapping("api/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "일정 생성", description = "일정 제목, 내용, 이름, 비밀번호 입력시 생성완료")
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(requestDto,userDetails.getUser()));
    }


    @Operation(summary = "전체 일정 조회", description = "조건없이 전체일정 조회")
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        return ResponseEntity.ok().body(todoService.getTodos());
    }


    @Operation(summary = "선택 일정 조회", description = "파라미터에 id값 제시하면 해당 일정 조회")
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok().body(todoService.getTodoById(id));
    }

    @Operation(summary = "일정 수정", description = "비밀번호 일치하면 해당 id값의 일정을 찾아 제목, 내용, 담당자 수정 가능")
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(todoService.updateTodo(id,requestDto,userDetails.getUser()));
    }

    @Operation(summary = "일정 삭제", description = "비밀번호 일치하면 해당 id 값의 일정을 찾아 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        todoService.deleteTodo(id,requestDto,userDetails.getUser());
        return ResponseEntity.ok().body("성공적으로 일정 삭제");
    }
}
