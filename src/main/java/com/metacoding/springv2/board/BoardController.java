package com.metacoding.springv2.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;
import com.metacoding.springv2.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 내용을 수정하는 RestAPI 입니다.
     * @param id 게시글 ID
     * @param reqDTO 수정할 제목과 내용
     * @param sessionUser 현재 로그인된 사용자
     * @return 수정된 데이터 정보를 담은 응답 객체
     */
    @PutMapping("/api/boards/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody BoardRequest.UpdateDTO reqDTO, @AuthenticationPrincipal User sessionUser) {
        // 서비스에서 수정 로직을 처리합니다. (AuthController 패턴에 맞게 var 사용)
        var respDTO = boardService.게시글수정하기(id, reqDTO, sessionUser);
        return Resp.ok(respDTO);
    }

    /**
     * 게시글을 삭제하는 RestAPI 입니다.
     * @param id 삭제할 게시글 ID
     * @param sessionUser 현재 로그인된 사용자
     * @return 성공 응답
     */
    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, @AuthenticationPrincipal User sessionUser) {
        // 서비스에서 삭제 로직을 처리합니다. (AuthController 패턴에 맞게 var 사용)
        boardService.게시글삭제하기(id, sessionUser);
        return Resp.ok(null);
    }

    /**
     * 게시글 상세 정보를 반환하는 RestAPI 입니다.
     * @param id 게시글 ID
     * @return 게시글 상세 DTO를 포함한 응답 객체
     */
    @GetMapping("/api/boards/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
        // 서비스에서 게시글 상세 정보를 조회합니다. (AuthController 패턴 적용)
        var respDTO = boardService.게시글상세보기(id);
        return Resp.ok(respDTO);
    }

    /**
     * 게시글 목록을 반환하는 RestAPI 입니다.
     * @return 게시글 목록 DTO 리스트를 포함한 응답 객체
     */
    @GetMapping("/api/boards")
    public ResponseEntity<?> list() {
        // 서비스에서 게시글 목록을 조회합니다. (AuthController 패턴 적용)
        var respDTOs = boardService.게시글목록보기();
        return Resp.ok(respDTOs);
    }
}
