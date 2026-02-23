package com.metacoding.springv2.board;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.springv2._core.handler.ex.Exception403;
import com.metacoding.springv2._core.handler.ex.Exception404;
import com.metacoding.springv2.user.User;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true) // 클래스 전체에 읽기 전용 트랜잭션을 기본으로 적용합니다.
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시글 상세 내용을 조회하여 DTO로 반환합니다.
     * user를 fetch join으로 함께 조회하여 Lazy 로딩 N+1 문제를 방지합니다.
     * 
     * @param id 게시글 ID
     * @return 게시글 상세 DTO (id, title, content, userId, username)
     */
    public BoardResponse.DetailDTO 게시글상세보기(Integer id) {
        // user를 fetch join으로 함께 조회합니다. 없으면 404 예외 발생.
        var board = boardRepository.findByIdWithUser(id)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다"));

        return new BoardResponse.DetailDTO(board);
    }

    /**
     * 게시글을 수정하고 수정된 정보를 반환합니다.
     * 
     * @param id          게시글 ID
     * @param reqDTO      수정할 내용 (title, content)
     * @param sessionUser 현재 로그인된 사용자
     * @return 수정된 게시글 DTO (id, title, content, userId, username)
     */
    @Transactional
    public BoardResponse.UpdateDTO 게시글수정하기(Integer id, BoardRequest.UpdateDTO reqDTO, User sessionUser) {
        // 1. 수정할 게시글이 있는지 확인하고 작성자 정보를 함께 가져옵니다.
        var boardPS = boardRepository.findByIdWithUser(id)
                .orElseThrow(() -> new Exception404("수정할 게시글을 찾을 수 없습니다"));

        // 2. 권한을 확인합니다. (게시글 작성자와 로그인 유저가 일치하는지)
        if (!boardPS.getUser().getId().equals(sessionUser.getId())) {
            throw new Exception403("게시글을 수정할 권한이 없습니다.");
        }

        // 3. 엔티티 내용을 업데이트 합니다. (JPA의 변경 감지 기능을 이용)
        boardPS.update(reqDTO.getTitle(), reqDTO.getContent());

        // 4. 수정된 엔티티를 DTO로 변환하여 반환합니다.
        return new BoardResponse.UpdateDTO(boardPS);
    }

    /**
     * 게시글을 삭제합니다.
     * @param id 삭제할 게시글 ID
     * @param sessionUser 현재 로그인된 사용자
     */
    @Transactional
    public void 게시글삭제하기(Integer id, User sessionUser) {
        // 1. 삭제할 게시글이 존재하는지 확인합니다. (작성자 확인을 위해 findByIdWithUser 사용)
        var boardPS = boardRepository.findByIdWithUser(id)
                .orElseThrow(() -> new Exception404("삭제할 게시글을 찾을 수 없습니다."));

        // 2. 권한을 확인합니다.
        if (!boardPS.getUser().getId().equals(sessionUser.getId())) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다.");
        }

        // 3. 게시글을 삭제합니다. (댓글은 Cascade 설정에 의해 자동 삭제됨)
        boardRepository.delete(boardPS);
    }

    /**
     * 게시글 목록을 조회하여 DTO 리스트로 반환합니다.
     * 
     * @return 게시글 목록 DTO 리스트
     */
    public List<BoardResponse.ListDTO> 게시글목록보기() {
        // 모든 게시글을 조회하여 DTO 리스트로 변환합니다.
        var boardList = boardRepository.findAll();

        // ::new 대신 람다식으로 변환합니다.
        return boardList.stream()
                .map(b -> new BoardResponse.ListDTO(b))
                .collect(Collectors.toList());
    }
}
