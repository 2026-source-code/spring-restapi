package com.metacoding.springv2.board;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 목록을 조회하여 DTO 리스트로 반환합니다.
     * @return 게시글 목록 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<BoardResponse.ListDTO> 게시글목록보기() {
        // 1. DB에서 모든 게시글을 조회합니다.
        List<Board> boardList = boardRepository.findAll();

        // 2. 조회된 엔티티 리스트를 DTO 리스트로 변환하여 반환합니다.
        return boardList.stream()
                .map(b -> new BoardResponse.ListDTO(b))
                .collect(Collectors.toList());
    }
}
