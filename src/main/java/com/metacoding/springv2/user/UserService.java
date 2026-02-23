package com.metacoding.springv2.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.springv2._core.handler.ex.Exception404;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // 회원 정보 조회 로직
    @Transactional(readOnly = true)
    public UserResponse.DetailDTO 회원정보보기(Integer id) {
        // 리포지토리에서 ID로 유저를 검색하고, 없으면 404 예외 발생
        User userPS = userRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 유저를 찾을 수 없습니다"));
        // 조회된 유저 엔티티를 DTO로 변환하여 반환
        return new UserResponse.DetailDTO(userPS);
    }
}