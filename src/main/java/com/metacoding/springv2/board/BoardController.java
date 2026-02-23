package com.metacoding.springv2.board;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 목록을 반환하는 RestAPI 입니다.
     * @return 게시글 목록 DTO 리스트를 포함한 응답 객체
     */
    @GetMapping("/api/boards")
    public ResponseEntity<?> list() {
        // 1. 서비스에서 게시글 목록 DTO 리스트를 가져옵니다.
        List<BoardResponse.ListDTO> respDTOs = boardService.게시글목록보기();

        // 2. Resp.ok를 사용하여 성공 응답을 반환합니다.
        return Resp.ok(respDTOs);
    }
}
