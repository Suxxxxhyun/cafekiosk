package sample.cafekiosk.spring.domain.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자를 생성하되, 접근수준을 protected로 하자!
@Entity
//BaseEntity를 통해, 엔터티의 생성시간, 변경시간을 트랙킹할 수 있다.
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    @Enumerated(EnumType.STRING) // ENUM에서 사용하는 문자열을 DB에 저장하겠다!
    private ProductType type;

    @Enumerated(EnumType.STRING) // ENUM에서 사용하는 문자열을 DB에 저장하겠다!
    private ProductSellingStatus sellingStatus;

    private String name;

    private int price;

    @Builder
    private Product(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }
}
