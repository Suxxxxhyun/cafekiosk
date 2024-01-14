## Layered Architecture
- Presentation
- Business
  - 비즈니스 로직을 구현하는 역할
  - Persistence Layer와의 상호작용(Data를 읽고 쓰는 행위)을 통해 비즈니스 로직을 개선시킨다.
  - **트랜잭션을 보장해야한다.**
- Persistence
    - Data Access의 역할
    - 비즈니스 가공로직이 포함되어서는 안된다.
    - Data에 대한 CRUD만 집중한 레이어

## CascadeType.ALL이 뭐야?
```java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderProduct> orderProducts;
```
- 부모는 One에 해당하고, 자식은 Many에 해당한다.
- 예를 들어, Order엔터티가 삭제되었을때, 해당 엔터티와 연관되어있는 OrderProduct엔터티가 함께 삭제되거나, 
- Order엔터티를 저장할때, Order엔터티에 담겨있던 OrderProduct엔터티를 한꺼번에 저장할 수 있다.
- 이를 **영속성 전이**라고 하며, **엔터티의 상태를 변경할때, 해당 엔터티와 연관된 엔터티의 상태변화를 전파시키는 옵션**이라고 할 수 있다.
- 부모가 변경될때, 자식도 변경됨.

