package store;

public class CartItem {
    private Product product;
    private Integer buyCount;

    public CartItem(Product product, Integer buyCount) {
        this.product = product;
        this.buyCount = buyCount;
    }

    public void checkCartItem() {
        product.validateTotalQuantity(buyCount);

        Integer freeCount = product.checkFreeQuantity(buyCount);
        if (freeCount > 0) {
            UserInputOutputHandler.printFreeCountQuestion(product.getName(), freeCount);
            boolean isBuyFreeCount = UserInputOutputHandler.readWantOrNot();
            if (isBuyFreeCount) {
                this.buyCount = this.buyCount + freeCount;
            }
        }

        Integer notIncludedPromotionCount = product.checkNotIncludedPromotionCount(buyCount);
        if (notIncludedPromotionCount != 0) {
            UserInputOutputHandler.printPromotionNotIncludedQuestion(product.getName(), notIncludedPromotionCount);
            boolean isBuyOk = UserInputOutputHandler.readWantOrNot();
            if (!isBuyOk) {
                this.buyCount = 0;
            }
        }
    }

    boolean isNotBuying() {
        return this.buyCount == 0;
    }

    public Integer calcPromotionCount() {
        return product.calcPromotionCount(buyCount);
    }

    public Integer calcPromotionPrice() {
        return product.calcPromotionPrice(buyCount);
    }

    public Integer calcRawPrice() {
        return product.calcRawPrice(this.buyCount);
    }

    public Integer calcPrice() {
        return product.calcPrice(this.buyCount);
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    @Override
    public String toString() {
        return "" +
                product.getName() + "\t" + buyCount + "\t" + product.calcRawPrice(buyCount);
    }
}
