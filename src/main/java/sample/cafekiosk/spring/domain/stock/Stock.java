package sample.cafekiosk.spring.domain.stock;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    private int quantity; //재고수량

    @Builder
    private Stock(Long id, String productNumber, int quantity) {
        this.id = id;
        this.productNumber = productNumber;
        this.quantity = quantity;
    }

    public static Stock create(String productNumber, int quantity){
        return Stock.builder()
                .productNumber(productNumber)
                .quantity(quantity)
                .build();
    }

    //getter사용 지양하기 위함
    public boolean isQuantityLessThan(int quantity){
        return this.quantity < quantity;
    }

    //getter사용 지양하기 위함
    public void deductQuantity(int quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException("차감할 재고 수량이 없습니다.");
        }
        this.quantity -= quantity;
    }
}
