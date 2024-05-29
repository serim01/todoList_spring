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
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 생성", description = "해당 todoId 값의 일정을 찾아 댓글 내용과 사용자 입력시 생성완료")
    @PostMapping("/{todoId}")
    public CommentResponseDto createComment(@PathVariable Long todoId, @Valid @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(todoId,requestDto,userDetails.getUser());
    }

    @Operation(summary = "댓글 조회", description = "해당 todoId 값의 일정을 찾아 모든 댓글 내용, 작성자 조회")
    @GetMapping("/{todoId}")
    public List<CommentResponseDto> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }

    @Operation(summary = "댓글 수정", description = "해당 commentId 값의 댓글을 찾아 작성자가 일치하면 댓글내용 수정 가능")
    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId,requestDto,userDetails.getUser());
    }

    @Operation(summary = "댓글 삭제", description = "해당 commentId 값의 댓글을 찾아 작성자가 일치하면 댓글내용 삭제 가능")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(commentId, userDetails.getUser());
        return new ResponseEntity<>("댓글 삭제가 완료되었습니다.", HttpStatus.OK);
    }
}
