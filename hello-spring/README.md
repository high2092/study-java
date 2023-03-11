### 단축키 (IntelliJ, Mac)
- static import: Option + Enter
- 타입 자동완성: Option + Command + V
- Getter, Setter: Command + N

### 학습정리
- [@Autowired](https://iseunghan.tistory.com/63)
- 컴포넌트 스캔 vs. 자바 코드로 직접 등록
  - 자바 코드로 직접 등록하는 게 더 좋은 경우
    - 상황에 따라 구현 클래스를 바꿀 필요가 있는 경우

- 생성자 주입, 필드 주입, Setter 주입
  - Setter 주입의 단점
    - 런타임 중 빈을 바꿀 필요는 없음. 유지보수성 손실
  - 필드 주입의 단점
    - 중간에 못 바꿈 (*이게 왜 단점이지?* )
