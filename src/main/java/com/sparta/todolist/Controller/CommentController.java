package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Tag(name = "1. 댓글생성")
    @Operation(summary = "createComment", description = "댓글 내용과 사용자 입력시 생성완료")
    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDto requestDto){
        return commentService.createComment(id,requestDto);
    }

    @Tag(name = "2. 댓글수정")
    @Operation(summary = "updateComment", description = "해당 id값의 댓글을 찾아 작성자가 일치하면 댓글내용 수정 가능")
    @PutMapping("/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id,requestDto);
    }
}
