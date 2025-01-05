package store.promotion;

public class NoPromotion implements Promotion{
    @Override
    public Integer calcPromotionCount(int count) {
        return 0;
    }

    @Override
    public Integer calcFreeCount(int buyCount, int quantity) {
        return 0;
    }

    @Override
    public Integer calcNotIncludedPromotionCount(int buyCount, int quantity) {
        return 0;
    }
}
