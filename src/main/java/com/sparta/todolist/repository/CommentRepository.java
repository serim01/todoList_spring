package com.sparta.todolist.repository;

import com.sparta.todolist.entity.Comment;
import com.sparta.todolist.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTodoList(TodoList todoList);
}
