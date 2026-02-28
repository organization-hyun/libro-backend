# AGENTS.md (Codex / 에이전트 작업 규칙)

## 0) 목적
이 레포지토리(프로젝트)는 기존 "독서 기록 + 독서 모임"에서 **모바일 퍼스트 일상 기록/아카이빙 서비스**로 피벗합니다.  
Codex/에이전트는 `docs/pivot/` 문서를 **단일 진실(Single Source of Truth)** 로 사용합니다.

## 1) 최우선 규칙 (Golden Rules)
1. **작은 PR만**: 1 PR = 1 테마. 한 PR에서 여러 축을 동시에 바꾸지 않습니다.
2. `docs/pivot/PRD.md`에 없는 범위의 기능 확장은 **금지**합니다.
3. 큰 리네임/대규모 리팩토링보다 **호환 레이어(별칭/어댑터/매퍼)** 를 우선합니다.
4. MVP 스코프는 매우 타이트하게 유지합니다.
    - 최우선: **매일 기록 루프(작성 UX + 자동저장 + 즉시 보상 카드)**
    - 아카이브: 캘린더/리스트/검색/월간 리캡
    - 소셜: MVP는 **공유 링크(읽기 전용)** 까지만
    - 모임 운영(가입/권한/스레드 토론 등)은 **2차**
5. 코드 변경이 있으면 반드시 포함합니다.
    - 테스트 또는 명확한 수동 테스트 플랜
    - DB 변경 시 마이그레이션
    - 호환성/롤백 관점 설명

## 2) 필수 1단계: “분석 전용 PR”
피벗 구현 전에 반드시:
- 레포 전체를 스캔합니다.
- `docs/pivot/REPO_ANALYSIS.md`를 작성합니다(아래 목차 준수).
- 이 PR에서는 **프로덕션 코드 변경 금지**(docs/ci 템플릿 정도만 허용).

## 3) 필수 산출물: docs/pivot/REPO_ANALYSIS.md 목차
1. 레포 인벤토리
    - 프레임워크/언어/주요 폴더/빌드/배포
2. 인증 & 데이터 레이어
    - 인증 방식, DB, ORM/쿼리 레이어, 마이그레이션 방식
3. 현재 도메인 맵
    - Book / ReadingLog / Club / Member / Discussion 등 핵심 모델/테이블/API 정리
4. 피벗 영향도(Pivot Impact)
    - “책이 필수”라고 가정하는 지점 Top 10
    - “독서 기록만”을 가정하는 지점 Top 10
5. 단계별 마이그레이션 플랜(PR 단위)
    - Step 1: Entry 일반화 + 호환
    - Step 2: 모바일 퍼스트 UI 쉘 + 에디터 자동저장
    - Step 3: 오늘의 프롬프트
    - Step 4: 아카이브(캘린더/검색/리캡)
    - Step 5: 공유 링크(share_slug + visibility)
6. 리스크/불명확 사항
    - 테스트 부족, 경계 불명확, 성능 리스크 등

## 4) PR 운영 규칙
### PR 제목 네이밍
- `pivot/docs: ...`
- `pivot/db: ...`
- `pivot/api: ...`
- `pivot/ui: ...`
- `pivot/share: ...`

### 커밋 스타일
- Conventional commits 권장 (`feat:`, `refactor:`, `docs:`, `chore:` 등)

### Definition of Done (DoD)
- ✅ 로컬 빌드/실행 가능
- ✅ 테스트 통과 또는 수동 테스트 플랜 제공
- ✅ DB 변경 시 마이그레이션 포함
- ✅ 무관한 변경(diff) 없음
- ✅ 동작 변경 시 문서 업데이트

## 5) 구현 가이드(피벗 타겟)
### 도메인 일반화 매핑(목표)
- ReadingLog -> Entry
- Book -> Material(또는 초기에는 Book 유지 + Material 타입 확장)
- Club/Discussion -> 레포에 남기되 MVP에서는 확장/개편 금지(2차)

### MVP 핵심 엔티티(목표)
- Entry:
    - date, category(diary/book/movie/thought), content, tags
    - summary, keywords(보상 카드)
    - visibility(private/unlisted/public), share_slug
- Prompt:
    - category, question, is_active

### 모바일 퍼스트 UX(필수)
- 하단 탭 네비게이션(Home / Write / Archive / Recap)
- 에디터 자동저장 + 저장 상태 표시("저장됨")
- “3초 안에 쓰기 시작” 가능한 플로우

## 6) 불확실할 때의 기본 원칙
- `docs/pivot/CONTEXT.md`와 `docs/pivot/PRD.md`를 다시 읽고,
- 그래도 불확실하면 PR 설명에 2가지 옵션과 트레이드오프를 적고,
- **가장 작은 변경 + 가장 안전한 호환**을 기본 선택으로 합니다.