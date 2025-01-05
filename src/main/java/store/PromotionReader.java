package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PromotionReader {
    private static BufferedReader reader;

    public Map<String, Promotion> readPromotion() throws IOException {
        try {
            reader = new BufferedReader(new FileReader("src/main/resources/promotions.md"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        reader.readLine();
        HashMap<String, Promotion> map = new HashMap<>();
        String str;
        while((str = reader.readLine()) != null) {
            String[] tokens = str.split(",");
            Promotion promotion = new Promotion(
                    tokens[0],
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    LocalDate.parse(tokens[3]),
                    LocalDate.parse(tokens[4])
            );
            map.put(tokens[0], promotion);
        }
        reader.close();

        return map;
    }
}
