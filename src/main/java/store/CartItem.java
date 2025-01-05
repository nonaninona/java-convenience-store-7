package store;

public class CartItem {
    private Product product;
    private Integer buyCount;

    public CartItem(Product product, Integer buyCount) {
        this.product = product;
        this.buyCount = buyCount;
    }

    public void validateCartItem() {
        product.checkCount(buyCount);

        Integer freeCount = product.checkFreeCount(buyCount);
        if (freeCount > 0) {
            boolean isBuyFreeCount = UserInputOutputHandler.readWantOrNot();
            if (isBuyFreeCount) {
                this.buyCount = buyCount + freeCount;
            }
        }

        boolean isExist = product.checkIfNotIncludedPromotionCountExist(buyCount);
        if (isExist) {
            boolean isBuyOk = UserInputOutputHandler.readWantOrNot();
            if (!isBuyOk) {
                this.buyCount = 0;
            }
        }
    }

    @Override
    public String toString() {
        return "" +
                product.getName() + "\t" + buyCount + "\t" + product.calcRawPrice(buyCount);
    }
}
