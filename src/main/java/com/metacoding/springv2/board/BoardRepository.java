package com.metacoding.springv2.board;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 게시글 데이터 처리를 위한 리포지토리 인터페이스입니다.
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

    /**
     * 게시글과 작성자(user)를 fetch join으로 한 번에 조회합니다.
     * Lazy 로딩으로 인한 N+1 문제를 방지합니다.
     * @param id 게시글 ID
     * @return 게시글 (user 포함)
     */
    @Query("SELECT b FROM Board b JOIN FETCH b.user WHERE b.id = :id")
    Optional<Board> findByIdWithUser(@Param("id") Integer id);
}
