# REPO_ANALYSIS - pivot 사전 분석 (libro-backend)

작성일: 2026-02-28
대상 레포: `libro-backend` (Spring Boot + Kotlin + MySQL)
범위: 분석 전용(PRD/CONTEXT/ARCHITECTURE 기준), 프로덕션 코드 변경 없음

## 1. 레포 인벤토리

### 1-1. 기술 스택/프레임워크
- 언어: Kotlin 1.9.25
- 런타임: Java 17
- 프레임워크: Spring Boot 3.5.0
- 주요 모듈
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - QueryDSL (`querydsl-jpa`, `querydsl-apt`)
  - OpenAPI/Swagger (`springdoc-openapi-starter-webmvc-ui`)
- DB
  - 운영: MySQL (`mysql-connector-j`, `application.yml`)
  - 테스트: H2 in-memory (`application-test.yml`)

### 1-2. 주요 폴더 구조
- `src/main/kotlin/com/libro/librobackend/config`
  - 보안/JWT, CORS, QueryDSL, Swagger, JPA Auditing 설정
- `src/main/kotlin/com/libro/librobackend/domain`
  - `book`, `readingrecord`, `readingcompletion`, `readinggroup`, `user`, `userbook`
- `src/main/resources/application.yml`
  - datasource/jwt/cors 환경변수 기반 설정
- `src/test`
  - 현재는 `contextLoads()` 중심의 최소 테스트
- `docs/pivot`
  - 피벗 컨텍스트/PRD/아키텍처 문서(SSOT)

### 1-3. 빌드/실행/배포
- 빌드 도구: Gradle Wrapper (`./gradlew`)
- 빌드 설정: `build.gradle.kts`, `settings.gradle.kts`
- 컨테이너: `Dockerfile` (사전 빌드된 JAR를 복사해 실행)
- CI 파이프라인 설정 파일(`.github/workflows/*`)은 레포 내에서 확인되지 않음

### 1-4. 테스트 현황(분석 시점)
- 자동 테스트: `src/test/kotlin/.../LibroBackendApplicationTests.kt`의 스프링 컨텍스트 로딩 1건
- 로컬 실행 결과: `./gradlew test -q` 통과
- 커버리지 관점: 핵심 도메인 로직/권한/쿼리 회귀를 담보하기엔 부족

---

## 2. 인증 & 데이터 레이어

### 2-1. 인증 방식(현행)
- 방식: 이메일/비밀번호 로그인 + JWT Bearer 토큰
- 주요 흐름
  - `POST /api/auth/signup`
  - `POST /api/auth/login` -> JWT 발급
  - `JwtFilter`가 `Authorization: Bearer <token>`에서 userId 추출
  - `@CurrentUserId` 커스텀 리졸버로 컨트롤러 파라미터 주입
- 보안 설정
  - 세션 Stateless
  - `/api/auth/**`, Swagger 경로, `/error`만 permitAll
  - 나머지는 인증 필수

참고: PRD의 MVP 인증 목표는 "이메일 매직링크 로그인"이며, 현재 구현과 차이가 있음.

### 2-2. 데이터 레이어
- ORM: Spring Data JPA
- 커스텀 쿼리: QueryDSL (`ReadingRecordRepositoryImpl`, `ReadingGroupRepositoryImpl`)
- 엔티티 공통시간: `BaseTimeEntity` (`createdAt`, `updatedAt`) + JPA Auditing
- 관계 모델링
  - 다수 엔티티가 FK 객체 연관 대신 `userId`, `bookId`, `readingRecordId` 같은 scalar id를 필드로 보유
  - 예외: `ReadingGroup` <-> `SharedReadingRecord`는 JPA 연관 사용

### 2-3. 마이그레이션 방식
- `ddl-auto: none`
- Flyway/Liquibase 등 스키마 버전관리 도구 미적용
- `schema.sql`/`db/migration` 부재

해석: 현재는 애플리케이션 밖에서 스키마를 관리하는 전제에 가깝고, 피벗 단계에서 DB 변경 추적 가능성이 낮은 구조임.

---

## 3. 현재 도메인 맵

### 3-1. 핵심 엔티티/테이블(코드 기준)

| 도메인 | 엔티티 | 핵심 필드 | 비고 |
|---|---|---|---|
| User | `User` | `name`, `email`, `password` | JWT 로그인 기준 사용자 |
| Book | `Book` | `title`, `author`, `description` | 독립 도서 카탈로그 |
| User-Book 상태 | `UserBookStatus` | `userId`, `bookId`, `userBookStatusEnum(WISH/READING)` | 사용자-도서 상호작용 |
| Reading Log | `ReadingRecord` | `userId`, `bookId`, `review`, `reviewDate` | 독서 기록의 중심 |
| Reading Note | `Note` | `readingRecordId`, `content`, `pageNumber` | 기록 하위 메모 |
| Reading Completion | `ReadingCompletion` | `date`, `duration`, `bookId?`, `userId`, `note?` | 월별 독서 완료 통계성 기록 |
| Reading Group | `ReadingGroup` | `bookTitle`, `bookAuthor`, `description` | 모임 자체가 책 메타에 결합 |
| Shared Record | `SharedReadingRecord` | `readingGroup`, `readingRecordId` | 모임 내 기록 공유 링크 |

### 3-2. API 맵(컨트롤러 기준)
- Auth
  - `POST /api/auth/signup`
  - `POST /api/auth/login`
- Book
  - `POST /api/books`
  - `GET /api/books`
  - `GET /api/books/search`
  - `GET /api/books/{bookId}`
  - `POST /api/books/{bookId}/wish`
  - `GET /api/books/{bookId}/status`
- UserBook Interaction(중복 성격)
  - `POST /api/user-book/interaction/{bookId}/wish`
  - `GET /api/user-book/interaction/{bookId}/status`
- Reading Record
  - `GET /api/reading-records`
  - `GET /api/reading-records/{readingRecordId}`
  - `POST /api/reading-records`
  - `DELETE /api/reading-records/{readingRecordId}`
  - `POST /api/reading-records/{readingRecord}/note`
  - `GET /api/reading-records/{readingRecordId}/notes`
  - `DELETE /api/reading-records/{readingRecordId}/notes/{noteId}`
- Reading Completion
  - `POST /api/reading-completions`
  - `GET /api/reading-completions?year=&month=`
- Reading Group
  - `GET /api/reading-groups`
  - `GET /api/reading-groups/{readingGroupId}`
  - `GET /api/reading-groups/{readingGroupId}/shared-reading-records`
  - `POST /api/reading-groups/{readingGroupId}/shared-reading-record`

### 3-3. 도메인 경계 관찰
- `ReadingRecord` 조회는 `Book` 조인을 전제로 동작(제목/저자 응답 필수)
- `ReadingGroup`은 모임 도메인 자체가 `bookTitle`, `bookAuthor` 필드에 고정됨
- `BookController`와 `UserBookInteractionController`가 일부 책임 중복

---

## 4. 피벗 영향도(Pivot Impact)

### 4-1. “책이 필수”라고 가정하는 지점 Top 10
1. `ReadingRecord` 엔티티가 `bookId: Long`을 필수로 가짐.
2. `CreateReadingRecordRq`가 `bookId`만 입력받아 기록 생성.
3. `ReadingRecordCommandService.saveReadingRecord()`가 `ReadingRecord.create(bookId, userId)`를 호출.
4. `ReadingRecordQueryService`가 `BookRepository`를 직접 의존.
5. `ReadingRecordQueryService.getReadingRecordById()`가 `readingRecord.bookId`로 `Book`을 조회.
6. `ReadingRecordRepositoryImpl.findAllWithBookInfoByUserId()`가 `reading_record`와 `book` 조인을 강제.
7. `ReadingRecordRs`가 응답 필드로 `bookTitle`, `bookAuthor`를 고정 제공.
8. `BookController` 전반(`/api/books/**`)이 레코드 작성 전제 카탈로그 역할을 수행.
9. `UserBookStatus`/`UserBookInteraction`이 사용자 상태를 `bookId` 기준으로만 관리.
10. `ReadingGroup` 엔티티/응답이 `bookTitle`, `bookAuthor`를 핵심 필드로 고정.

### 4-2. “독서 기록만”을 가정하는 지점 Top 10
1. 패키지/리소스 명명 자체가 `readingrecord`, `readingcompletion`, `readinggroup` 중심.
2. 핵심 CRUD API 경로가 `/api/reading-records`로 도메인이 고정됨.
3. `ReadingRecord` 필드명이 `review`, `reviewDate`로 "독후감" 의미를 내포.
4. `Note`가 `pageNumber`를 기본 제공해 책 페이지 맥락을 전제.
5. `ReadingCompletion`이 "독서 완료"라는 이벤트 중심으로 설계됨.
6. `ReadingGroupController`의 기능명이 "독서 모임/독서기록 공유"로 고정.
7. `SharedReadingRecordDto`가 공유 콘텐츠를 `review`, `sharedDate(reviewDate)`로 표현.
8. `ReadingGroupRepositoryImpl`이 `readingRecord.review`, `readingRecord.reviewDate`를 직접 프로젝션.
9. Swagger Operation summary가 전반적으로 "독서" 용어에 고정.
10. `userbook` 도메인이 "책 상태"(WISH/READING)만 표현 가능해 diary/movie/thought를 포괄하지 못함.

---

## 5. 단계별 마이그레이션 플랜(PR 단위)

원칙: 작은 PR, 호환 레이어 우선, 기존 엔드포인트 즉시 제거 금지.

### Step 1: Entry 일반화 + 호환
- PR 제목 예: `pivot/db: Entry 일반화 스키마 도입 및 호환 레이어`
- 목표
  - 기존 `ReadingRecord`를 대체/확장할 `Entry` 개념 도입
  - 기존 API는 유지하되 내부 매핑으로 호환
- 제안 작업
  - DB: `entry`(또는 `reading_record` 확장) 스키마 추가
    - `entry_date`, `category`, `content`, `tags`, `summary`, `keywords`, `visibility`, `share_slug`(nullable)
  - API: 신규 `Entry` 서비스/리포지토리 추가, 기존 `reading-record` API는 어댑터 통해 `category=book`로 매핑
  - 응답 호환: 기존 `ReadingRecordRs` 형태를 유지하는 mapper 계층 추가
- 롤백/호환
  - 신규 테이블/컬럼 추가만 먼저 수행(파괴적 변경 금지)
  - 읽기 경로는 단계적으로 dual-read 또는 mapper 기반 유지

### Step 2: 모바일 퍼스트 UI 쉘 + 에디터 자동저장(백엔드 지원)
- PR 제목 예: `pivot/api: Entry 자동저장 API 및 저장상태 필드 지원`
- 목표
  - "3초 안에 쓰기 시작" 가능한 초안 저장 API 제공
- 제안 작업
  - `Entry` 임시저장/업데이트 API(짧은 주기 호출 전제)
  - 저장상태 표시를 위한 `updatedAt`/버전 필드 활용 정책 정리
  - 빈 콘텐츠 초안 생성 허용 여부 및 만료 정책 정의
- 롤백/호환
  - 기존 레코드 생성 API 유지
  - autosave 경로는 신규 클라이언트에서만 사용

### Step 3: 오늘의 프롬프트
- PR 제목 예: `pivot/api: 오늘의 프롬프트 도메인 추가`
- 목표
  - 카테고리별 질문 1개 랜덤 제공
- 제안 작업
  - DB: `prompt(id, category, question, is_active)`
  - API: `GET /api/prompts/today?category=`
  - (선택) 하루 N회 교체 정책을 위한 서버 카운트/제한 설계
- 롤백/호환
  - 프롬프트 기능은 완전 신규이므로 기존 기능 영향 낮음

### Step 4: 아카이브(캘린더/검색/리캡)
- PR 제목 예: `pivot/api: Entry 아카이브 조회 API`
- 목표
  - 캘린더/리스트/검색/월간 리캡을 위한 조회 계층 확립
- 제안 작업
  - 기간 조회, 키워드 검색, 카테고리 필터 API
  - 월간 리캡(키워드 Top/카테고리 분포/대표 기록) 집계 쿼리
  - 인덱스 전략(`user_id`, `entry_date`, `category`, `visibility`) 반영
- 롤백/호환
  - 읽기 API 신규 추가 위주, 기존 읽기 API 존치

### Step 5: 공유 링크(share_slug + visibility)
- PR 제목 예: `pivot/share: Entry 공유 링크 읽기 전용 API`
- 목표
  - 최소 소셜 요구사항(private/unlisted/public + slug) 구현
- 제안 작업
  - DB: `visibility`, `share_slug` 제약/인덱스 확정
  - API: 공유 링크 조회(인증 없이 read-only), 소유자만 공개범위/slug 변경
  - 보안: private 접근 차단, unlisted는 slug 소지자만 접근
- 롤백/호환
  - 공유는 신규 경로로 추가, 기존 모임 기능은 변경 최소화

---

## 6. 리스크/불명확 사항

### 6-1. 리스크
1. 스키마 마이그레이션 체계 부재(Flyway/Liquibase 미적용)로 배포 환경 간 drift 위험.
2. 테스트 부족(현재 contextLoads 중심)으로 피벗 중 회귀 탐지 어려움.
3. 다수 엔티티가 scalar FK만 사용해 참조 무결성/조인 안정성이 코드에 분산.
4. 인증 방식 불일치(PRD=매직링크, 현행=비밀번호+JWT)로 인증 축 피벗 필요.
5. `SharedReadingRecordDto.review: String` vs `ReadingRecord.review: String?` 타입 불일치 잠재 위험.
6. `BookController`와 `UserBookInteractionController` 책임 중복으로 API 혼선 가능.
7. 작성 UX 핵심인 autosave/partial update API가 아직 없음.
8. timezone/로컬 날짜(`entry_date`) 정책이 아직 명시되지 않아 아카이브 정합성 위험.
9. 검색/리캡용 인덱스/집계 전략 부재로 데이터 증가 시 성능 리스크.
10. 레거시 도메인(ReadingGroup 등) 유지 범위 미정 시 MVP 범위 침식 가능.

### 6-2. 불명확 사항(결정 필요)
1. Entry 저장 전략: 신규 `entry` 테이블 vs 기존 `reading_record` 확장.
2. `tags`, `keywords` 저장 형식: JSON 컬럼 vs 정규화 테이블.
3. `Material` 도입 시점: Step 1 즉시 도입 vs Book 호환 유지 후 지연 도입.
4. 공유 slug 정책: 자동 생성/재생성/충돌 처리/길이 규칙.
5. 매직링크 인증 전환 시 JWT와의 공존 기간 및 마이그레이션 시나리오.

### 6-3. 이번 분석 PR에서의 결론
- 권장 기본 선택: **가장 작은 변경 + 가장 안전한 호환**
  - Step 1에서는 기존 `reading-record`/`book` API를 유지하고 내부 매핑으로 `Entry`를 도입
  - 모임(`readinggroup`)은 유지하되 MVP 확장 대상에서 제외
  - 인증은 즉시 교체보다 공존 기간을 둔 점진 전환이 안전
