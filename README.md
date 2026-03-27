# 0. 프로젝트 개요
> SpringBoot 기반의 다양한 Backend 심화 기술들을 익히기 위한 연습용 프로젝트 입니다.

# 1. 프로젝트 기술 스펙
## 📌 Backend (서버 애플리케이션)
+ **Core Framework :** Spring Boot 3.5.11
+ **Language :** Java 17
+ **ORM / Persistence :** MyBatis
+ **Database :** PostgreSQL
+ **Build Tool :** Maven
+ **Boilerplate Reduction :** Lombok

## 📌 Frontend (UI / Client)
+ **Template Engine (SSR) :** Thymeleaf
+ **Language :** JavaScript (ES6+)
+ **Library :** jQuery 3.6+


# 2. 사용 기술
### ✅ **Gemini CLI**
+ **일자 :** 2026-03-12 ~ 2026-03-13
+ **목적**
    + LLM 이후 Agent AI 의 빠른 성장으로 인한 추후 도입 및 활용 가능성이 상당부분 존재하기 때문에 보다 높은 수준의 활용 역량 필요
+ **연습내역**
    + 프로젝트 기본 구조 세팅 (Scaffolding) 작업 간에 활용 
    + Gemini CLI 활용을 위한 기본 이론 학습
    + CLI Cache Reads 비율을 높이기 위한 GEMINI.md 파일 생성과 컨텍스트 엔지니어링 작업
    + GEMINI.md 파일 세분화와 PROGRESS.md 파일 생성을 통한 CLI 작업 체계화 및 Task & Working log 관리 진행

### ✅ **Docker**
+ **일자 :** 2026-03-12 ~ 2026-03-13
+ **목적**
    + 빌드 및 배포 과정에서 주류로 자리잡은 컨테이너 기반의 docker 및 K8s 활용 역량 필요
+ **연습내역**
    + DB 사용을 위한 기본 컨테이너 설정 (이미지, 볼륨, 포트, 네트워크 등) 과 이미지 추가
    + docker-compose 를 통한 다수 컨테이너 생성 및 실행

### ✅ **Session 관리**
+ **일자 :** 2026-03-16 ~ 2026-03-18
+ **목적**
    + 로그인 이후 세션 종료 전까지 로그인 정보의 전역적 관리
    + 비즈니스 로직에서의 Audit Column 사용 편의성 증대
+ **연습내역**
    + **Interceptor :** 모든 Controller 요청 전후를 가로챈 뒤 세션에서 유저 정보를 가져와 ThreadLocal 에 세팅
    + **ThreadLocal :** 특정 Thread 만 개별적으로 접근하여 Thread-safe 를 보장하는 독립 저장소 개념 활용

### ✅ **Spring Rest Client**
+ **일자 :** 2026-03-19 ~ 2026-03-20
+ **목적**
    + 다른 서버 또는 EAI, ESB 통신을 통한 API 연계 역량 향상        
+ **연습내역**
    + **RestClient :** RestClient 를 활용한 원/달러 환율 추출 예제를 통해 RestClient의 장점인 함수형(Flent API)와 코드 가독성 향상 경험
    + **Generic :** 환율 추출 예제 중 오류 해결 과정에서 Java 제네릭과 런타임 시 Type Erasure 에 대한 재학습

### ✅ **RestControllerAdvice**
+ **일자 :** 2026-03-24 ~ 2026-03-25
+ **목적**
    + 보일러 플레이트 제거와 관심사 분리를 통해 잦은 변경에 대응 가능한 코드 구조를 갖추고 코드의 확장성과 가독성 향상을 위해      
+ **연습내역**
    + **Static Factory Method 기법 :** 반복되는 builder 패턴 대신 정적 팩토리 메서드 기법을 활용한 공통 응답 객체 CommonResponseDto 생성 및 적용
    + **@RestControllerAdvice :** try-catch 보일러 플레이트 제거를 위한 전역 에러 처리 핸들러 GlobalExceptionHandler 생성 및 적용
    + **Enum :** Enum 과 개인 예외 클래스인 CustomException 생성을 통한 사용자 맞춤 에러 제공으로 전역 에러 처리 시 응답 코드 관리의 용이성과 확장성 향상 경험

### ✅ **Profile**
+ **일자 :** 2026-03-26
+ **목적**
    + github 연동 시 보안 확보  
+ **연습내역**
    + **${} 환경변수 :** github 연동 시 각종 민감한 정보가 포함된 설정 값들을 노출시키지 않음으로써 보안 확보
    + **Profile :** profile 분리를 통한 운영환경 별 관리 경험