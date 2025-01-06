package store;

import store.exception.StockExceedException;

public class Quantity {
    private Integer basicQuantity;
    private Integer promotionQuantity;

    public Quantity(Integer basicQuantity, Integer promotionQuantity) {
        this.basicQuantity = basicQuantity;
        this.promotionQuantity = promotionQuantity;
    }

    public static Quantity ZERO() {
        return new Quantity(0, 0);
    }

    public Integer getBasicQuantity() {
        return basicQuantity;
    }

    public Integer getPromotionQuantity() {
        return promotionQuantity;
    }

    public void add(Quantity quantity) {
        this.basicQuantity += quantity.getBasicQuantity();
        this.promotionQuantity += quantity.getPromotionQuantity();
    }

    public void minus(Integer quantity) {
        if (this.promotionQuantity >= quantity) {
            this.promotionQuantity -= quantity;
            return;
        }
        this.basicQuantity -= (quantity - this.promotionQuantity);
        this.promotionQuantity = 0;
    }

    public void validateIfCanMinusCount(int count) {
        if (this.promotionQuantity + this.basicQuantity < count) {
            throw new StockExceedException();
        }
    }

    public boolean isPromotionQuantityGreaterThan(int count) {
        return this.promotionQuantity > count;
    }

    public boolean isPromotionQuantityLessThan(int count) {
        return this.promotionQuantity < count;
    }

    public boolean isBasicQuantityGreaterThan(int count) {
        return this.basicQuantity > count;
    }
}
