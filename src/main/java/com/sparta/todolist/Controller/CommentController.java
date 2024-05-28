package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @Valid @RequestBody CommentRequestDto requestDto){
        return commentService.createComment(id,requestDto);
    }
}
