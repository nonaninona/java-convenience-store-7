package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductReader {
    private static BufferedReader reader;

    public Map<String, Product> readProduct(Map<String, Promotion> promotionMap) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/products.md"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        reader.readLine();
        HashMap<String, Product> map = new HashMap<>();
        String str;
        while((str = reader.readLine()) != null) {
            String[] tokens = str.split(",");

            Product product = map.get(tokens[0]);
            if(product == null) {
                product = new Product(
                        tokens[0],
                        Integer.parseInt(tokens[1]),
                        0,
                        0,
                        promotionMap.get(tokens[3])
                );
            }

            int count = Integer.parseInt(tokens[2]);
            if(tokens[3].equals("null"))
                product.incraseCount(count);
            else
                product.incrasePromotionCount(count);

            map.put(tokens[0], product);
        }
        reader.close();

        return map;
    }
}
