### RequiredArgsConstructor란?

- 생성자 주입
- final 혹은 @NotNull이 붙은 필드를 기반으로 생성자를 자동으로 만들어줌
- 한번 의존성을 주입받은 객체는 프로그램이 끝날때까지 변하지 않음(불변성)

### JUnit5

- 단위테스트를 위한 테스트프레임워크

### AssertJ

- 테스트코드 작성을 원활하게 돕는 테스트라이브러리
- 메서드 체이닝 지원(.이용해서 계속 작성하는 것)

```jsx
//assertEquals - JUnit에 있는 메소드
assertEquals(americano.getName(), "아메리카노");
//assertThat - AssertJ에 있는 메소드
assertThat(americano.getName()).isEqualTo("아메리카노");
```

- AssertJ에서 제공하는 예외를 위한 메소드 - asserThatThrownBy

```text
//assertThatThrownBy - 예외를 위한 메소드
assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("음료는 1잔 이상 주문하실 수 있습니다.");
```

### 어떤 상황이 주어질때의 테스트코드는?
```text
public Order createOrder(LocalDateTime currentDateTime){
    //LocalDateTime currentDateTime = LocalDateTime.now();
    LocalTime currentTime = currentDateTime.toLocalTime();
    if(currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)){
        throw new IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.");
    }
    return new Order(LocalDateTime.now(), beverages);
}
```

createOrder의 파라미터를 LocalDateTime으로 받아서, 테스트할때 내가 원하는 시간을 직접 넣어줄 수 있도록 한다.

```text
@Test 
    void createOrderWithCurrentTime(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    cafeKiosk.add(americano);
    Order order = cafeKiosk.createOrder(LocalDateTime.of(2023, 8, 23, 14, 0));
    assertThat(order.getBeverages()).hasSize(1);
    assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
}

@Test
    void createOrderOutsideOpenTime(){
    CafeKiosk cafeKiosk = new CafeKiosk();
    Americano americano = new Americano();
    cafeKiosk.add(americano);
    assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(2023, 8, 23, 9, 59)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.");
}
```

### 추가학습
- lombok (@Setter, @Data, @AllArgsConstructor는 지양 → 난잡하게 쓰면 쓸수록 유지보수 하기 어려움.)

- DTO에서만 toString을 사용하는 편이다. entity에서 @ToString을 사용하면, 양방향 연관관계시 순환참조 문제 발생




