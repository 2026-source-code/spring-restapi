package com.metacoding.springv2.auth;

import com.metacoding.springv2.user.User;

import lombok.Data;

/**
 * 인증 관련 응답 데이터를 담는 클래스입니다.
 */
public class AuthResponse {

    /**
     * 회원가입 완료 후 사용자에게 반환할 정보를 담는 DTO입니다.
     */
    @Data
    public static class DTO {
        private Integer id;
        private String username;
        private String roles;

        // 엔티티를 응답 DTO로 변환하는 생성자입니다.
        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.roles = user.getRoles();
        }
    }
}