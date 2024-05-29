package com.sparta.todolist.repository;

import com.sparta.todolist.entity.TodoList;
import com.sparta.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findAllByOrderByCreatedAtDesc();

    Optional<TodoList> findByIdAndUser(Long id, User user);
}
