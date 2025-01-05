package store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void 제품_재고_조정() {
        // given
        Product cider = new Product("사이다", 1000, 5, 10, Promotion.noPromotion);

        //when
        cider.decreaseQuantity(2);
        cider.decreasePromotionCount(3);

        //then
        assertThat(cider.getQuantity()).isEqualTo(3);
        assertThat(cider.getPromotionQuantity()).isEqualTo(7);
    }
}
