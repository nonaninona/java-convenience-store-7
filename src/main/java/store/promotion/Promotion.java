package store.promotion;

import store.Quantity;

public interface Promotion {
    Integer calcPromotionCount(Quantity quantity);
    Integer calcFreeCount(int buyCount, Quantity quantity);
    Integer calcNotIncludedPromotionCount(int buyCount, Quantity quantity);
}
