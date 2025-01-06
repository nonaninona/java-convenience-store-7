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
    private final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private final String DELIMETER = ",";
    private final String NO_PROMOTION = "null";

    public ProductReader() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(PRODUCT_FILE_PATH));
    }

    public Map<String, Product> readProduct(Map<String, Promotion> promotionMap) throws IOException {
        reader.readLine();
        HashMap<String, Product> map = new HashMap<>();
        String str;
        while ((str = reader.readLine()) != null) {
            String[] tokens = str.split(DELIMETER);
            Product product = map.get(tokens[0]);
            Promotion promotion = promotionMap.get(tokens[3]);
            if (promotion == null) {
                promotion = new NoPromotion();
            }
            if (product == null) {
                product = new Product(tokens[0], Integer.parseInt(tokens[1]), Quantity.ZERO(), promotion);
            }

            int quantity = Integer.parseInt(tokens[2]);
            Quantity q;
            if (tokens[3].equals(NO_PROMOTION)) {
                q = new Quantity(quantity, 0);
            } else {
                q = new Quantity(0, quantity);
            }
            product.fill(q);

            map.put(tokens[0], product);
        }
        reader.close();

        return map;
    }
}
