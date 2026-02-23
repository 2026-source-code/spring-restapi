package com.metacoding.springv2.reply;

import lombok.Data;

/**
 * 댓글 관련 요청 데이터를 담는 클래스입니다.
 */
public class ReplyRequest {

    /**
     * 댓글 쓰기 요청을 위한 DTO입니다.
     */
    @Data
    public static class WriteDTO {
        private String comment;
        private Integer boardId;
    }
}
