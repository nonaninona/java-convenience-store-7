package store.promotion;

import store.Quantity;

public class NoPromotion implements Promotion{
    @Override
    public Integer calcPromotionCount(Quantity quantity) {
        return 0;
    }

    @Override
    public Integer calcFreeCount(int buyCount, Quantity quantity) {
        return 0;
    }

    @Override
    public Integer calcNotIncludedPromotionCount(int buyCount, Quantity quantity) {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
