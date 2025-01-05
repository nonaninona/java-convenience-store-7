package store.promotion;

public interface Promotion {
    Integer calcPromotionCount(int count);
    Integer calcFreeCount(int buyCount, int quantity);
    Integer calcNotIncludedPromotionCount(int buyCount, int quantity);
}
