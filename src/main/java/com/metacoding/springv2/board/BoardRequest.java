package com.metacoding.springv2.board;

import lombok.Data;

/**
 * 게시글 관련 요청 데이터를 담는 클래스입니다.
 */
public class BoardRequest {

    /**
     * 게시글 수정을 위한 요청 DTO입니다.
     */
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
