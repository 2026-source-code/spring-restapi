package com.metacoding.springv2.reply;

import lombok.Data;

/**
 * 댓글 관련 응답 데이터를 담는 클래스입니다.
 */
public class ReplyResponse {

    /**
     * 댓글 쓰기 완료 후 응답을 위한 DTO입니다.
     */
    @Data
    public static class WriteDTO {
        private Integer id;
        private String comment;
        private Integer userId;
        private String username;
        private Integer boardId;

        public WriteDTO(Reply reply) {
            this.id = reply.getId();
            this.comment = reply.getComment();
            this.userId = reply.getUser().getId();
            this.username = reply.getUser().getUsername();
            this.boardId = reply.getBoard().getId();
        }
    }
}
