package sample.cafekiosk.spring.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * select *
     * from product
     * where selling_type in ('SELLING', 'HOLD');
     */
    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    List<Product> findAllByProductNumberIn(List<String> productNumbers);

    //역순으로 정렬된 것중 가장 상위의 것을 가져와라. (마지막에 등록된 상품!)
    @Query(value = "select p.product_number from product p order by id desc limit 1", nativeQuery = true)
    String findLatestProduct();
}
