package com.sparta.todolist.entity;

import com.sparta.todolist.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {
    /*
    아이디 (고유번호) : bigint
    댓글 내용 : varchar
    사용자 아이디 : varchar
    일정 아이디 : bigint
    작성일자 : timestamp
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500)
    private String contents;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="todo_id")
    private TodoList todoList;

    public Comment(CommentRequestDto requestDto, TodoList todoList, User user) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.todoList = todoList;
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
