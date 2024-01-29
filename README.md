### 기능 요구사항(입문)
- [✅] 주문 목록에 음료 추가/삭제 
  - [✅] 한 종류의 음료 여러잔을 한번에 담는 기능
- [✅] 주문 목록 전체 지우기
- [✅] 주문 목록 총 금액 계산하기
- [✅] 주문 생성하기
  - [✅] 가게 운영시간(10:00 ~ 22:00) 외에는 주문을 할 수 없다.

---
### 기능 요구사항(실전)
- [✅] 키오스크 주문을 위한 상품 후보 리스트 조회하기
- [✅] 상품의 판매 상태 : 판매중, 판매보류, 판매중지
  - 판매중, 판매보류인 상태의 상품을 화면에 보여준다.
  - 판매보류인 경우 : 재고가 없는 경우
- [✅] 화면에 필요한 정보는 다음과 같다.
  - id, 상품번호, 상품타입, 판매상태, 상품이름, 가격
- [✅] 상품 번호 리스트를 받아 주문 생성하기
  - 주문은 주문상태, 주문 등록시간을 가진다.
  - 주문의 총 금액을 계산할 수 있어야한다.
- [✅] 주문 생성 시 재고 확인 및 개수 차감 후 생성하기
  - 재고는 상품번호를 가진다.
  - 재고와 관련있는 상품 타입은 병음료, 베이커리이다.
- Cafe Kiosk Admin
  - [✅] 관리자 페이지에서 신규 상품을 등록할 수 있다.
    - 상품명, 타입, 판매상태, 가격 등을 기반으로 상품을 등록한다.

### 학습로그
- [어떤 상황이 주어질때의 테스트코드는 어떻게 작성하는게 좋을까?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section2.md)
- [What's BDD?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section4.md)
- [Spring시 알아야할 기초개념](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section5(1).md)
- [기본 생성자를 추가할때, AccessLevel을 PRIVATE이 아닌 PROTECTED를 하는 이유는?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section5(2).md)
- [CascadeType.ALL이 뭐야?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section5(3).md)
- [deleteAll()보다 deleteAllInBatch()가 더 권장되는 이유가 뭐야?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section5(4).md)
- [@SpringBootTest, @DataJpaTest의 차이는?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section5(5).md)
- [MockMvc, @Transactional(readOnly = true)와 @Transactional을 분리하라고?](https://github.com/Suxxxxhyun/cafekiosk-tdd-study/blob/main/learning-log/learning-log-section5(6).md)
- [objectMapper로 string형태의 json을 직렬화, 역직렬화한다고?]()
- [도메인 정책에 따른 특수한 형태의 Validation은 controller에서 하지 않는다.]()
- [controller와 service간의 의존관계가 생겼다! 이를 위한 해결책은?]()
