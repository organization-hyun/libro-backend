# 아키텍처 노트 (Pivot)

## 원칙
- 작은 변경, 호환 레이어 우선
- 빅뱅 리네임/대규모 리팩토링 지양
- 기존 API/플로우가 있으면 최대한 유지하면서 내부적으로 Entry로 매핑

## 타겟 데이터 모델(개념)
### Entry
- id, user_id
- entry_date(date)
- category(diary/book/movie/thought)
- content(text)
- tags(string[])
- summary(text, nullable)
- keywords(string[])
- visibility(private/unlisted/public)
- share_slug(nullable, unique)
- prompt_id(nullable)
- created_at, updated_at

### Prompt
- id
- category
- question
- is_active

## 마이그레이션 전략(PR 단계)
- Step 1: 기존 ReadingLog 확장 또는 Entry 테이블 도입 + 어댑터로 호환
- Step 2: 모바일 퍼스트 UI 쉘(탭바) + 자동저장 에디터
- Step 3: 오늘의 프롬프트
- Step 4: 아카이브(캘린더/검색/월간 리캡)
- Step 5: 공유 링크(share_slug + visibility) + 읽기 전용 페이지

## 호환성(Backward compatibility)
- 기존 Book/ReadingLog 기반 엔드포인트가 있다면:
    - 엔드포인트는 유지하되 내부적으로 Entry로 매핑
    - DB 컬럼은 유지하거나 view/compat layer 제공
    - 점진적으로 신규 API로 유도