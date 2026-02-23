package com.metacoding.springv2.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원 정보 보기 (아이디로 조회)
    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
        // 회원 정보를 서비스에서 조회
        var respDTO = userService.회원정보보기(id);
        // 조회된 정보를 담아 성공 응답 반환
        return Resp.ok(respDTO);
    }
}
