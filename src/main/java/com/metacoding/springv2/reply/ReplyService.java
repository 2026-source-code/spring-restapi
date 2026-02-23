package com.metacoding.springv2.reply;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.springv2._core.handler.ex.Exception403;
import com.metacoding.springv2._core.handler.ex.Exception404;
import com.metacoding.springv2.board.BoardRepository;
import com.metacoding.springv2.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    /**
     * 새로운 댓글을 저장합니다.
     * @param reqDTO 댓글 정보 (내용, 게시글 ID)
     * @param sessionUser 댓글 작성자 (세션 정보)
     * @return 저장된 댓글의 DTO
     */
    @Transactional
    public ReplyResponse.WriteDTO 댓글쓰기(ReplyRequest.WriteDTO reqDTO, User sessionUser) {
        // 1. 댓글이 달릴 게시글이 존재하는지 확인합니다.
        var boardPS = boardRepository.findById(reqDTO.getBoardId())
                .orElseThrow(() -> new Exception404("댓글을 달 게시글을 찾을 수 없습니다."));

        // 2. Reply 엔티티를 생성하여 저장합니다.
        var reply = Reply.builder()
                .comment(reqDTO.getComment())
                .board(boardPS)
                .user(sessionUser)
                .build();

        replyRepository.save(reply);

        // 3. 저장된 엔티티를 응답 DTO로 변환하여 반환합니다.
        return new ReplyResponse.WriteDTO(reply);
    }

    /**
     * 댓글을 삭제합니다.
     * @param id 삭제할 댓글 ID
     * @param sessionUser 현재 로그인된 유저
     */
    @Transactional
    public void 댓글삭제하기(Integer id, User sessionUser) {
        // 1. 삭제할 댓글이 존재하는지 확인합니다.
        var replyPS = replyRepository.findById(id)
                .orElseThrow(() -> new Exception404("삭제할 댓글을 찾을 수 없습니다."));

        // 2. 권한을 확인합니다. (댓글 작성자와 로그인 유저가 일치하는지)
        if (!replyPS.getUser().getId().equals(sessionUser.getId())) {
            throw new Exception403("댓글을 삭제할 권한이 없습니다.");
        }

        // 3. 댓글을 삭제합니다.
        replyRepository.delete(replyPS);
    }
}
