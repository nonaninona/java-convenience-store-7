package store;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            cider.decreaseCount(2);
            cider.decreasePromotionCount(3);
            System.out.println(cider);
        }
    }
}
