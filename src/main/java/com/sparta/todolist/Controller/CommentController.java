package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.security.UserDetailsImpl;
import com.sparta.todolist.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "CommentController", description = "댓글 관련 api")
@RequestMapping("/api/todo/{todoId}/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "해당 todoId 값의 일정을 찾아 댓글 내용과 사용자 입력시 생성완료")
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long todoId, @Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(todoId,requestDto,userDetails.getUser()));
    }

    @Operation(summary = "댓글 조회", description = "해당 todoId 값의 일정을 찾아 모든 댓글 내용, 작성자 조회")
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long todoId) {
        return ResponseEntity.ok().body(commentService.getComments(todoId));
    }

    @Operation(summary = "댓글 수정", description = "해당 commentId 값의 댓글을 찾아 작성자가 일치하면 댓글내용 수정 가능")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long todoId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(commentService.updateComment(todoId, commentId,requestDto,userDetails.getUser()));
    }

    @Operation(summary = "댓글 삭제", description = "해당 commentId 값의 댓글을 찾아 작성자가 일치하면 댓글내용 삭제 가능")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long todoId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(todoId, commentId, userDetails.getUser());
        return ResponseEntity.ok().body("성공적으로 댓글 삭제");
    }
}
