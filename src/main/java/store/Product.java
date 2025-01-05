package store;

public class Product {
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer promotionQuantity;
    private Promotion promotion;

    public Product(String name, Integer price, Integer quantity, Integer promotionQuantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionQuantity = promotionQuantity;
        this.promotion = promotion;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPromotionQuantity() {
        return promotionQuantity;
    }

    public void checkCount(int buyCount) {
        if (this.promotionQuantity + this.quantity < buyCount) {
            throw new StockExceedException();
        }
    }

    public Integer checkFreeCount(int buyCount) {
        if (buyCount <= this.promotionQuantity) {
            Integer freeCount = promotion.calcFreeCount(buyCount);
            if (freeCount != 0 && buyCount + freeCount <= this.promotionQuantity) {
                UserInputOutputHandler.printFreeCountQuestion(this.name, freeCount);
                return freeCount;
            }
        }
        return 0;
    }

    public boolean checkIfNotIncludedPromotionCountExist(int buyCount) {
        if (buyCount > this.promotionQuantity) {
            Integer totalPromotionCount = promotion.calcTotalPromotionCount(this.promotionQuantity);
            Integer notPromotionCount = buyCount - totalPromotionCount;
            if (!promotion.isNoPromotion() && notPromotionCount != 0) {
                UserInputOutputHandler.printPromotionNotIncludedQuestion(this.name, notPromotionCount);
                return true;
            }
        }
        return false;
    }

    public Integer calcRawPrice(int buyCount) {
        return this.price * buyCount;
    }

    public Integer calcPrice(int buyCount) {
        if(buyCount <= this.promotionQuantity) {
            Integer promotionCount = promotion.calcPromotionCount(buyCount);
            Integer totalPrice = (buyCount - promotionCount) * this.price;

            this.promotionQuantity -= buyCount;
            return totalPrice;
        }

        Integer promotionCount = promotion.calcPromotionCount(this.promotionQuantity);
        Integer totalPrice = (buyCount - promotionCount) * this.price;

        Integer leftCount = buyCount - this.promotionQuantity;
        this.promotionQuantity = 0;
        this.quantity -= leftCount;
        return totalPrice;
    }

    public void increaseQuantity(int quantity) {
        this.quantity+=quantity;
    }

    public void increasePromotionQuantity(int quantity) {
        this.promotionQuantity+=quantity;
    }

    public boolean decreaseQuantity(int quantity) {
        if(this.quantity <= quantity)
            return false;
        this.quantity-=quantity;
        return true;
    }

    public boolean decreasePromotionCount(int count) {
        if(this.promotionQuantity < count)
            return false;
        this.promotionQuantity-=count;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(this.quantity > 0) {
            stringBuilder.append("-").append(" ");
            stringBuilder.append(this.name).append(" ");
            stringBuilder.append(this.price).append("원").append(" ");
            stringBuilder.append(this.quantity).append("개").append("\n");
        }
        if(this.promotionQuantity > 0) {
            stringBuilder.append("-").append(" ");
            stringBuilder.append(this.name).append(" ");
            stringBuilder.append(this.price).append("원").append(" ");
            stringBuilder.append(this.promotionQuantity).append("개").append(" ");
            stringBuilder.append(this.promotion).append("\n");
        }
        return stringBuilder.toString();
    }
}
