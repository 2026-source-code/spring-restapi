package com.metacoding.springv2.user;

import lombok.Data;

/**
 * 사용자 정보와 관련된 응답 데이터를 담는 클래스입니다.
 */
public class UserResponse {

    /**
     * 사용자 상세 정보를 보여주기 위한 DTO입니다.
     */
    @Data
    public static class DetailDTO {
        private Integer id;
        private String username;
        private String email;

        // 엔티티의 데이터를 DTO로 옮겨 담는 생성자입니다.
        public DetailDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}
