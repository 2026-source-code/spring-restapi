package com.metacoding.springv2.auth;

import lombok.Data;

/**
 * 인증 관련 요청 데이터를 담는 클래스입니다.
 */
public class AuthRequest {
    /**
     * 회원가입 시 필요한 정보를 담는 DTO입니다.
     */
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;
    }

    /**
     * 로그인 시 필요한 정보를 담는 DTO입니다.
     */
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }
}