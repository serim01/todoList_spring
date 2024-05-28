package com.sparta.todolist.service;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.repository.CommentRepository;
import com.sparta.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoListRepository todoListRepository;

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 일정은 존재하지 않습니다.")
        );

        Comment comment = commentRepository.save(new Comment(requestDto,todoList));
        return new CommentResponseDto(comment);
    }
}
