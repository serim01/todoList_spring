package com.sparta.todolist.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("비밀번호가 옳지 않습니다.");
    }
}