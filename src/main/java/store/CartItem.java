package store;

public class CartItem {
    private Product product;
    private Integer buyCount;

    public CartItem(Product product, Integer buyCount) {
        this.product = product;
        this.buyCount = buyCount;
    }

    public void checkCartItem() {
        product.validateQuantity(buyCount);

        Integer freeCount = product.checkFreeQuantity(buyCount);
        if (freeCount > 0) {
            UserInputOutputHandler.printFreeCountQuestion(product, freeCount);
            boolean isBuyFreeCount = UserInputOutputHandler.readWantOrNot();
            if (isBuyFreeCount) {
                this.buyCount = this.buyCount + freeCount;
            }
        }

        Integer notIncludedPromotionCount = product.checkNotIncludedPromotionCount(buyCount);
        if (notIncludedPromotionCount > 0) {
            UserInputOutputHandler.printPromotionNotIncludedQuestion(product, notIncludedPromotionCount);
            boolean isBuyOk = UserInputOutputHandler.readWantOrNot();
            if (!isBuyOk) {
                this.buyCount = 0;
            }
        }
    }

    boolean isNotBuying() {
        return this.buyCount == 0;
    }

    public Integer calcPrice() {
        return product.calcPrice(this.buyCount);
    }

    public Integer calcPromotedPrice() {
        return product.calcPromotedPrice(this.buyCount);
    }

    public void buy() {
        product.buy(this.buyCount);
    }

    public String toBillListString() {
        return product.getName() + "\t\t" +
                buyCount + "\t\t" +
                UserInputOutputHandler.formatMoney(product.calcPrice(buyCount));
    }

    public String toPromotedListString() {
        if(product.calcPromotedCount(buyCount) == 0)
            return "";
        return product.getName() + "\t\t" +
                UserInputOutputHandler.formatMoney(product.calcPromotedCount(buyCount));
    }

    public Integer getBuyCount() {
        return buyCount;
    }
}
