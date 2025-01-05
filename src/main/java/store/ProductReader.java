package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import store.promotion.NoPromotion;
import store.promotion.Promotion;

public class ProductReader {
    private static BufferedReader reader;

    public ProductReader() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("src/main/resources/products.md"));
    }

    public Map<String, Product> readProduct(Map<String, Promotion> promotionMap) throws IOException {
        reader.readLine();
        HashMap<String, Product> map = new HashMap<>();
        String str;
        while ((str = reader.readLine()) != null) {
            String[] tokens = str.split(",");

            Product product = map.get(tokens[0]);
            Promotion promotion = promotionMap.get(tokens[3]);
            if (promotion == null) {
                promotion = new NoPromotion();
            }
            if (product == null) {
                product = new Product(tokens[0], Integer.parseInt(tokens[1]), 0, 0, promotion);
            }

            int quantity = Integer.parseInt(tokens[2]);
            if (tokens[3].equals("null")) {
                product.increaseQuantity(quantity);
            } else {
                product.increasePromotionQuantity(quantity);
            }

            map.put(tokens[0], product);
        }
        reader.close();

        return map;
    }
}
