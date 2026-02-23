package com.metacoding.springv2.reply;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;
import com.metacoding.springv2.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {

    private final ReplyService replyService;

    /**
     * 댓글을 작성하는 RestAPI 입니다.
     * @param reqDTO 댓글 정보 (내용, 게시글 ID)
     * @param sessionUser 인증된 현재 사용자
     * @return 저장된 댓글 정보가 담긴 응답 객체
     */
    @PostMapping("/api/replies")
    public ResponseEntity<?> save(@RequestBody ReplyRequest.WriteDTO reqDTO, @AuthenticationPrincipal User sessionUser) {
        // 서비스에서 댓글 쓰기 로직을 처리합니다. (AuthController 패턴에 맞게 var 사용)
        var respDTO = replyService.댓글쓰기(reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    /**
     * 댓글을 삭제하는 RestAPI 입니다.
     * @param id 삭제할 댓글 ID
     * @param sessionUser 인증된 현재 사용자
     * @return 성공 응답
     */
    @DeleteMapping("/api/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, @AuthenticationPrincipal User sessionUser) {
        // 서비스에서 댓글 삭제 로직을 처리합니다. (AuthController 패턴에 맞게 var 사용)
        replyService.댓글삭제하기(id, sessionUser);
        return Resp.ok(null);
    }
}
