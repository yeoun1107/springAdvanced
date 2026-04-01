## 0. 필수 규칙
- 이 파일을 읽고 현재 프로젝트의 진행 상태와 맥락을 파악할 것
- 모든 작업 완료 후, 세션 종료 전, 언제나 반드시 이 파일을 현재 진행상황에 맞게 업데이트하고 규모가 큰 변경이나 수정 및 추가 작업을 진행한 경우 이 파일을 업데이트할지 물어볼 것
- **[백업 원칙]**: 2일마다 한 번씩 `2. 작업 완료 내역`에서 현재 날짜가 아닌 과거의 작업 완료 내역에 대해 임의로 생략 기호(`...`)를 절대 사용하지 말고, 날짜별로 내역을 잘라내어 `ProgressHistory` 폴더 내에 `yyyy-mm-dd_history.md` 파일로 저장할 것.
- **[요약 원칙]**: `ProgressHistory` 폴더에 백업된 과거 날짜의 내역은 본 파일(`PROGRESS.md`)의 `2. 작업 완료 내역`에서 핵심 내용만 간단히 요약하여 유지할 것.
- 이 파일을 읽는 즉시, 백업 원칙과 요약 원칙에 따라 PROGRESS.md 파일의 백업 및 요약을 진행할 것.

## 1. 프로젝트 개요
- **프로젝트명**: Study Spring (학습용 웹 서비스)
- **주요 기술 스택**: Spring Boot 3.5.11, Java 17, PostgreSQL 17, MyBatis, Thymeleaf, javascript(ES6+), jquery 3.6+
- **최종 업데이트**: 2026-03-27


## 2. 작업 완료 내역 (History)
- **2026-03-27**
	- [Backend] 코드 그룹 삭제 시 하위 상세 코드 일괄 Soft Delete 처리 구현 (데이터 정합성 강화)
	- [Backend] `CodeServiceImpl.deleteCodeGroup` 에 `@Transactional` 적용 및 연계 삭제 로직(Cascade Soft Delete) 추가
	- [BugFix] `CodeMapper.xml` 내 PostgreSQL 문법 오류 수정 (문자열 리터럴 큰따옴표 -> 작은따옴표 변경)
	- [Test] `CodeServiceTest` 에 그룹 삭제 시 하위 상세 코드의 `use_yn` 상태까지 검증하는 TDD 케이스 보강
	- [Standard] `GEMINI.md`, `GEMINI_BE.md`, `GEMINI_FE.md` 엔지니어링 표준 완전 숙지 및 적용

- **2026-03-24** (요약)
	- [Backend] 전역 에러 처리 핸들러 구축 및 공통 응답 구조 확립, [Refactor] 로그인 로직 고도화
	- (상세 내역은 `ProgressHistory/2026-03-24_history.md` 참조)

- **2026-03-19** (요약)
	- [Practice] Spring RestClient 외부 API 연동 실습 및 환율 대시보드 UI 구현
	- (상세 내역은 `ProgressHistory/2026-03-19_history.md` 참조)

- **2026-03-18** (요약)
	- [DB/API] 코드 관리 마스터-디테일 CRUD 구현 및 UI/UX 고도화, [Standard] 엔지니어링 표준 수립
	- (상세 내역은 `ProgressHistory/2026-03-18_history.md` 참조)

- **2026-03-16** (요약)
	- [DB] `tb_user` 컬럼명 변경, [Infra] 전역 세션 및 유저 컨텍스트 관리 체계 구축
	- (상세 내역은 `ProgressHistory/2026-03-16_history.md` 참조)

- **2026-03-13** (요약)
	- [Infra] 로그 최적화, [API] 로그인/중복체크 구현, [DB] 테이블 규격화, [Test] TDD 검증 통과

- **2026-03-12** (요약)
	- 프로젝트 기본 인프라 환경 구축 및 사용자 테이블 설계


## 3. 현재 진행 상태
- **전역 예외 처리(GlobalExceptionHandler) 및 공통 응답 체계 구축 완료**
- **Spring 3.2+ Rest Client를 활용한 외부 API 연동 실습 완료**
- **공통 세션 관리 및 유저 컨텍스트 기반 전역 공유 체계 구축 완료**
- **반응형 공통 레이아웃 및 대시보드 UI 고도화 완료 (CSS 완전 분리)**
- **코드 관리(Master-Detail) 연계 삭제(Soft Delete) 로직 및 데이터 정합성 강화 완료**
- 로그인/회원가입 인증 프로세스 및 페이지 이동 연동 완료


## 4. 향후 작업 로드맵 (To-Do)
### 단기 과제
- **BCrypt 암호화 적용**: 회원가입 시 비밀번호 평문 저장 방식을 **Spring Security의 BCryptPasswordEncoder**를 활용한 암호화 방식으로 전환
- **기존 도메인 예외 처리 리팩토링**: `createSignup` 등 아직 기존 방식으로 구현된 로직에 `CustomException` 적용 및 보일러플레이트 제거

### 중장기 과제
- 게시판(Board) 도메인 생성 및 CRUD 구현
- RESTful API 문서화 (Swagger 등)


## 5. 참고 사항
- 모든 작업은 `GEMINI.md` 와 `GEMINI_BE.md` 와 `GEMINI_FE.md`의 **규칙들** 을 언제나 반드시 준수할 것
