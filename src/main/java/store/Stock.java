package store;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    Map<String, Promotion> promotionHashMap;
    Map<String, Product> productMap;

    public Stock() throws IOException {
        this.promotionHashMap = new HashMap<>();
        this.productMap = new HashMap<>();

        PromotionReader promotionReader = new PromotionReader();
        promotionHashMap = promotionReader.readPromotion();

        ProductReader productReader = new ProductReader();
        productMap = productReader.readProduct(promotionHashMap);
    }

    public void printStocks() {
        System.out.println("안녕하세요. W 편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        System.out.println();

        productMap.forEach((name, product) -> {
            System.out.print(product);
        });

        System.out.println();
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public List<Order> validateOrder(List<Order> orderList) throws StockExceedException {
        return orderList.stream().map(order -> {
            Product product = productMap.get(order.product());
            Integer buyCount = order.buyCount();

            product.checkCount(buyCount);

            Integer freeCount = product.checkFreeCount(buyCount);
            if (freeCount > 0) {
                boolean isBuyFreeCount = UserInputOutputHandler.readWantOrNot();
                if (isBuyFreeCount) {
                    return new Order(order.product(), order.buyCount() + freeCount);
                }
            }

            boolean isExist = product.checkIfNotIncludedPromotionCountExist(buyCount);
            if (isExist) {
                boolean isBuyOk = UserInputOutputHandler.readWantOrNot();
                if (isBuyOk) {
                    return order;
                }
                return new Order(order.product(), 0);
            }

            return order;
        }).toList();
    }

    public Integer calcRawriace(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(order -> {
                    Product product = productMap.get(order.product());
                    return product.calcRawPrice(order.buyCount());
                })
                .sum();
    }

    public Integer calcPrice(List<Order> orderList) {
        return orderList.stream()
                .mapToInt(order -> {
                    Product product = productMap.get(order.product());
                    return product.calcPrice(order.buyCount());
                })
                .sum();
    }
}
