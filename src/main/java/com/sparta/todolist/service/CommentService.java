package com.sparta.todolist.service;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.repository.CommentRepository;
import com.sparta.todolist.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoListRepository todoListRepository;

    public CommentResponseDto createComment(Long todoId, CommentRequestDto requestDto) {
        TodoList todoList = todoListRepository.findById(todoId).orElseThrow(() ->
                new NullPointerException("해당 일정은 존재하지 않습니다.")
        );

        Comment comment = commentRepository.save(new Comment(requestDto,todoList));
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getComments(Long todoId) {
        TodoList todoList = todoListRepository.findById(todoId).orElseThrow(() ->
                new NullPointerException("해당 일정은 존재하지 않습니다.")
        );
        List<Comment> commentList = commentRepository.findByTodoList(todoList);

        return commentList.stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new NullPointerException("해당 댓글은 존재하지않습니다.")
        );
        if(!comment.getUsername().equals(requestDto.getUsername())){
            throw new IllegalArgumentException("해당 댓글 작성자가 아닙니다.");
        }

        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new NullPointerException("해당 댓글은 존재하지않습니다.")
        );
        if(!comment.getUsername().equals(requestDto.getUsername())){
            throw new IllegalArgumentException("해당 댓글 작성자가 아닙니다.");
        }
        commentRepository.delete(comment);
    }
}
