package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Tag(name = "1. 댓글생성")
    @Operation(summary = "createComment", description = "해당 todoId 값의 일정을 찾아 댓글 내용과 사용자 입력시 생성완료")
    @PostMapping("/{todoId}")
    public CommentResponseDto createComment(@PathVariable Long todoId, @Valid @RequestBody CommentRequestDto requestDto){
        return commentService.createComment(todoId,requestDto);
    }

    @Tag(name = "2. 댓글조회")
    @Operation(summary = "getComments", description = "해당 todoId 값의 일정을 찾아 모든 댓글 내용, 작성자 조회")
    @GetMapping("/{todoId}")
    public List<CommentResponseDto> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }

    @Tag(name = "3. 댓글수정")
    @Operation(summary = "updateComment", description = "해당 commentId 값의 댓글을 찾아 작성자가 일치하면 댓글내용 수정 가능")
    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(commentId,requestDto);
    }

    @Tag(name = "4. 댓글삭제")
    @Operation(summary = "deleteComment", description = "해당 commentId 값의 댓글을 찾아 작성자가 일치하면 댓글내용 삭제 가능")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        commentService.deleteComment(commentId, requestDto);
        return new ResponseEntity<>("댓글 삭제가 완료되었습니다.", HttpStatus.OK);
    }
}
