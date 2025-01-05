package store;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;

public class Application {
    public static void main(String[] args) {
        PromotionReader promotionReader = new PromotionReader();
        Map<String, Promotion> promotionHashMap = new HashMap<>();
        try {
            promotionHashMap = promotionReader.readPromotion();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ProductReader productReader = new ProductReader();
        Map<String, Product> productMap = new HashMap<>();
        try {
            productMap = productReader.readProduct(promotionHashMap);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Product cider = productMap.get("사이다");
        if(cider != null) {
            System.out.println(cider);
            cider.decreaseQuantity(2);
            cider.decreasePromotionCount(3);
            System.out.println(cider);
        }

        List<Product> cart = new ArrayList<>();
        List<Integer> buyCounts = new ArrayList<>();
        productMap.forEach((name, product) -> {
//            if(!name.equals("사이다"))
//                return;
            System.out.println(product);
            Integer buyCount = Randoms.pickNumberInRange(1, 10);
//            Integer buyCount = 2;
            System.out.println("구매 : " + buyCount);

            if(!product.checkCount(buyCount))
                return;

            Integer freeCount = product.checkFreeCount(buyCount);
            if(freeCount > 0) {
                Integer buyOrNot = Randoms.pickNumberInRange(0, 1);
                boolean isBuy = buyOrNot == 1;
                System.out.println("구매결정 : " + isBuy);
                buyCount += buyOrNot * freeCount;
            }

            if(!product.checkPromotionCount(buyCount)) {
                Integer buyOrNot = Randoms.pickNumberInRange(0, 2);
                boolean isBuy = buyOrNot == 1;
                System.out.println("구매결정 : " + isBuy);
                if(!isBuy)
                    return;
            }

            cart.add(product);
            buyCounts.add(buyCount);
        });

        Integer rawTotalPrice = 0;
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            Integer buyCount = buyCounts.get(i);

            System.out.println(product);
            Integer price = product.calcRawPrice(buyCount);
            System.out.println("가격 : " + price);
            rawTotalPrice += price;
        }
        System.out.println("rawTotalPrice : " + rawTotalPrice);

        System.out.println("=".repeat(20));
        System.out.println(cart);
        System.out.println(buyCounts);

        Integer totalPrice = 0;
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            Integer buyCount = buyCounts.get(i);

            System.out.println(product);
            Integer price = product.calcPrice(buyCount);
            System.out.println("가격 : " + price);
            System.out.println(product);
            totalPrice += price;
        }
        System.out.println("totalPrice : " + totalPrice);

        Integer buyOrNot = Randoms.pickNumberInRange(0, 1);
        boolean isBuy = buyOrNot == 1;
        System.out.println("멤버쉽 여부 : " + isBuy);
        if(isBuy)
            totalPrice -= MemberShipDiscount.calcDiscount(rawTotalPrice);
        System.out.println("totalPrice : " + totalPrice);
    }
}
