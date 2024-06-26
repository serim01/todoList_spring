package com.sparta.todolist.service;

import com.sparta.todolist.dto.CommentRequestDto;
import com.sparta.todolist.dto.CommentResponseDto;
import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoService todoService;

    public CommentResponseDto createComment(Long todoId, CommentRequestDto requestDto, User user) {
        Todo todo = todoService.findTodoList(todoId);
        Comment comment = commentRepository.save(new Comment(requestDto, todo,user));
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getComments(Long todoId) {
        Todo todo = todoService.findTodoList(todoId);
        List<Comment> commentList = commentRepository.findByTodo(todo);

        return commentList.stream()
                .map(CommentResponseDto::new)
                .toList();
    }

    @Transactional
    public CommentResponseDto updateComment(Long todoId, Long commentId, CommentRequestDto requestDto, User user) {
        todoService.findTodoList(todoId);
        Comment comment = findComment(commentId);
        findUser(comment, user);

        comment.update(requestDto);

        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long todoId, Long commentId, User user) {
        todoService.findTodoList(todoId);
        Comment comment = findComment(commentId);
        findUser(comment, user);
        commentRepository.delete(comment);
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }

    private void findUser(Comment comment, User user) {
        if(!comment.getUser().getUsername().equals(user.getUsername())){
            throw new IllegalArgumentException("해당 댓글 작성자가 아닙니다.");
        }
    }

}
