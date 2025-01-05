package store.promotion;

import java.time.LocalDate;
import store.UserInputOutputHandler;

public class PlusPromotion implements Promotion{
    private String name;
    private Integer buy;
    private Integer get;
    private LocalDate startDate;
    private LocalDate endDate;

    public PlusPromotion(String name, Integer buy, Integer get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer calcPromotionCount(int count) {
        if(LocalDate.now().isAfter(endDate) || LocalDate.now().isBefore(startDate))
            return 0;

        return count / (buy + get);
    }

    @Override
    public Integer calcFreeCount(int buyCount, int promotionQuantity) {
        if(LocalDate.now().isAfter(endDate) || LocalDate.now().isBefore(startDate))
            return 0;

        Integer leftCount = buyCount % (buy + get);
        if (leftCount < buy) {
            return 0;
        }
        Integer freeCount = get - (leftCount - buy);
        if (freeCount != 0 && buyCount + freeCount <= promotionQuantity) {
            return freeCount;
        }
        return 0;
    }

    @Override
    public Integer calcNotIncludedPromotionCount(int buyCount, int quantity) {
        if(LocalDate.now().isAfter(endDate) || LocalDate.now().isBefore(startDate))
            return 0;

        Integer totalPromotionCount = calcTotalPromotionCount(quantity);
        return buyCount - totalPromotionCount;
    }

    @Override
    public String toString() {
        return this.name;
    }

    private Integer calcTotalPromotionCount(int count) {
        return calcPromotionCount(count) * (buy + get);
    }
}
