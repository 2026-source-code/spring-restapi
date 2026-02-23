package com.metacoding.springv2.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    // 유저네임 중복체크
    @GetMapping("/username-check")
    public ResponseEntity<?> usernameCheck(String username) {
        // 서비스에서 중복 체크 로직 수행
        authService.유저네임중복체크(username);
        // 중복되지 않으면 성공 응답 반환
        return Resp.ok(null);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody AuthRequest.JoinDTO reqDTO) {
        var respDTO = authService.회원가입(reqDTO);
        return Resp.ok(respDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest.LoginDTO reqDTO) {
        String accessToken = authService.로그인(reqDTO);
        return Resp.ok(accessToken);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "health ok";
    }
}
