# 프로젝트 분석 보고서 V2

## 1. 기술 스택 (Tech Stack)
- **Framework**: Spring Boot 3.5.5
- **Language**: Java 21
- **Database**: H2 (In-memory)
- **ORM**: Spring Data JPA
- **Security**: Spring Security, JWT (java-jwt 4.3.0)
- **Utility**: Lombok, Validation

## 2. 아키텍처 및 패턴
- **Controller-Service-Repository**: 표준 레이어드 아키텍처 준수.
- **REST API**: `/api/**` 경로를 통한 RESTful 웹 서비스 제공.
- **DTO**: 모든 요청/응답은 `@Data`가 적용된 내부 클래스 형태의 DTO 사용 (평탄화된 구조 권장).
- **Global Exception Handling**: `GlobalExceptionHandler`와 커스텀 예외(`Exception4xx`, `500`)를 통한 전역 예외 처리.
- **Security Pattern**: JWT를 이용한 Stateless 인증. `JwtAuthorizationFilter`를 통해 `/api/**` 및 `/admin/**` 경로 권한 제어.

## 3. 주요 기능 분석
### 3.1 인증 및 유저 (Auth & User)
- **회원가입/로그인**: BCrypt 암호화 및 JWT 토큰 발급.
- **유저네임 중복체크**: 중복 여부 확인 API 제공.
- **회원 정보 조회**: `/api/users/{id}` (인증 필요).

### 3.2 게시판 (Board)
- **목록보기**: `/api/boards` (평탄화된 DTO 리스트 반환).
- **상세보기**: `/api/boards/{id}` (작성자 정보를 포함한 평탄화된 DTO 반환).
- **리팩토링**: `rule1.md`에 따라 `var` 키워드 사용 및 내부 클래스 제거를 통한 최적화 완료.

### 3.3 댓글 (Reply)
- 엔티티 및 기본 클래스 구성 완료 (추가 기능 구현 대기 중).

## 4. 코드 컨벤션 및 규칙 (rule1.md)
- **CORS**: `SecurityConfig`에서 중앙 관리.
- **주석**: 모든 신규/수정 코드에 친절한 한글 주석 적용.
- **스타일**: `AuthController` 패턴 준수 (`var` 사용, 간결한 로직).
- **정리**: 불필요한 중복 코드(과거 CORS 필터 등) 및 설정값 제거 완료.

## 5. 향후 과제
- 댓글(Reply) 관련 API 기능 구체화 및 구현.
- API 문서화 (Spring RestDocs 적용 여부 검토).
- 통합 테스트 코드 보강.
