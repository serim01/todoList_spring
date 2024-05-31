package com.sparta.todolist.entity;

import com.sparta.todolist.dto.TodoListRequestDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter //조심히 사용!!..
@Table(name = "todo") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class TodoList extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 500)
    private String contents;
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public TodoList(TodoListRequestDto requestDto,User user) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password=requestDto.getPassword();
        this.user = user;
    }

    public void update(@Valid TodoListRequestDto requestDto,User user) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.user = user;
    }
}