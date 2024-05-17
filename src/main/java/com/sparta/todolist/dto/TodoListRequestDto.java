package com.sparta.todolist.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoListRequestDto {
    @Email(message = "이메일 형식에 맞춰 입력해주세요")
    private String username;

    @Size(min=1, max=10, message = "최소 1글자 이상 10글자 이하만 입력이 가능합니다.")
    private String title;

    private String contents;

    @NotNull(message = "비밀번호를 입력하세요.")
    private String password;
}
