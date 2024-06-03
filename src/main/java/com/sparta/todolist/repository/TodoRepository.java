package com.sparta.todolist.repository;

import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByCreatedAtDesc();

    Optional<Todo> findByIdAndUser(Long id, User user);
}
