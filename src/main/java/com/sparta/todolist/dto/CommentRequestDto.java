package com.sparta.todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    @NotNull(message = "댓글 내용을 입력하세요.")
    private String contents;
    @Email(message = "이메일 형식에 맞춰 입력해주세요")
    private String username;
}
