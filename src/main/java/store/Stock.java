package store;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import store.promotion.Promotion;

public class Stock {
    Map<String, Promotion> promotionHashMap;
    Map<String, Product> productMap;

    public Stock() throws IOException {
        PromotionReader promotionReader = new PromotionReader();
        ProductReader productReader = new ProductReader();

        this.promotionHashMap = promotionReader.readPromotion();
        this.productMap = productReader.readProduct(promotionHashMap);
    }

    public String productsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("안녕하세요. W 편의점입니다.").append("\n");
        stringBuilder.append("현재 보유하고 있는 상품입니다.").append("\n").append("\n");

        productMap.forEach((name, product) -> {
            stringBuilder.append(product.toSellInfoString());
        });

        stringBuilder.append("\n");
        stringBuilder.append("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])").append("\n");
        return stringBuilder.toString();
    }

    public List<CartItem> makeCartItems(List<Order> orderList) {
        return orderList.stream().map(order -> {
            Product product = productMap.get(order.productName());
            Integer buyCount = order.buyCount();
            return new CartItem(product, buyCount);
        }).collect(Collectors.toList());
    }
}
