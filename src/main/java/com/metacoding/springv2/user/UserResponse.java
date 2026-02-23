package com.metacoding.springv2.user;

import lombok.Data;

public class UserResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String username;
        private String email;

        public DetailDTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}
