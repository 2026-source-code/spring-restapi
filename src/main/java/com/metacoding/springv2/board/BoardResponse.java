package com.metacoding.springv2.board;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * 게시글 관련 응답 DTO를 모아두는 클래스입니다.
 */
public class BoardResponse {

    /**
     * 게시글 목록 보기 응답을 위한 DTO입니다.
     */
    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String content;

        // 엔티티를 DTO로 변환하기 위한 생성자입니다.
        public ListDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    /**
     * 게시글 상세보기 응답을 위한 DTO입니다.
     * (리팩토링: 내부 클래스를 제거하고 평탄화된 구조를 가집니다.)
     */
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private List<ReplyDTO> replies; // 게시글에 달린 댓글 리스트

        // 엔티티를 DTO로 변환하기 위한 생성자입니다.
        public DetailDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            // 댓글 리스트를 DTO 리스트로 변환합니다.
            this.replies = board.getReplies().stream()
                    .map((r)-> new ReplyDTO(r))
                    .toList();
        }

        /**
         * 게시글에 포함될 댓글 정보를 담는 DTO입니다.
         */
        @Data
        public static class ReplyDTO {
            private Integer id;
            private String comment;
            private Integer userId;
            private String username;

            public ReplyDTO(com.metacoding.springv2.reply.Reply reply) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
            }
        }
    }

    /**
     * 게시글 수정 완료 후 응답을 위한 DTO입니다.
     */
    @Data
    public static class UpdateDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;

        public UpdateDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
        }
    }
}
