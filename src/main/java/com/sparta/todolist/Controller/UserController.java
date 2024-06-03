package com.sparta.todolist.Controller;

import com.sparta.todolist.dto.LoginRequestDto;
import com.sparta.todolist.dto.SignupRequestDto;
import com.sparta.todolist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j(topic = "UserController")
@Controller
@RequiredArgsConstructor
@Tag(name = "UserController", description = "사용자 관련 api")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "별명, 사용자이름, 비밀번호는 필수입력이며, Admin 계정은 별도 과정 필요함.")
    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 정보가 잘못되었습니다.");
        }

        userService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공\nHttpStatus : "+ HttpStatus.OK);
    }
    @Operation(summary = "로그인", description = "사용자 이름, 비밀번호로 로그인해야함.")
    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok().body("로그인 성공!");
    }

}