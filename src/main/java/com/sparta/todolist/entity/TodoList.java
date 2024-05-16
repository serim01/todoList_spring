package com.sparta.todolist.entity;

import com.sparta.todolist.dto.TodoListRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter //조심히 사용!!..
@Table(name = "todo") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class TodoList extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    public TodoList(TodoListRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password=requestDto.getPassword();
    }

    public void update(TodoListRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}