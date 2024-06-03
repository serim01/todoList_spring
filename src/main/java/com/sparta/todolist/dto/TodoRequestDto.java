package com.sparta.todolist.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoRequestDto {
    @Size(min=1, max=200, message = "최소 1글자 이상 200글자 이하만 입력이 가능합니다.")
    private String title;

    private String contents;

    @NotNull(message = "비밀번호를 입력하세요.")
    private String password;
}
