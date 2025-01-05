package store;

import store.exception.StockExceedException;
import store.promotion.Promotion;

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

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPromotionQuantity() {
        return promotionQuantity;
    }

    public void validateTotalQuantity(int buyCount) {
        if (this.promotionQuantity + this.quantity < buyCount) {
            throw new StockExceedException();
        }
    }

    public Integer checkFreeQuantity(int buyCount) {
        if (buyCount <= this.promotionQuantity) {
            return promotion.calcFreeCount(buyCount, this.promotionQuantity);
        }
        return 0;
    }

    public Integer checkNotIncludedPromotionCount(int buyCount) {
        if (buyCount > this.promotionQuantity) {
            return promotion.calcNotIncludedPromotionCount(buyCount, this.promotionQuantity);
        }
        return 0;
    }

    public Integer calcRawPrice(int buyCount) {
        return this.price * buyCount;
    }

    public Integer calcPromotionCount(int buyCount) { return promotion.calcPromotionCount(buyCount); }

    public Integer calcPrice(int buyCount) {
        Integer totalPromotionCount = Math.min(buyCount, this.promotionQuantity);
        Integer promotionCount = promotion.calcPromotionCount(buyCount);
        Integer totalPrice = (buyCount - promotionCount) * this.price;

        this.promotionQuantity -= totalPromotionCount;
        this.quantity -= Math.max(buyCount - this.promotionQuantity, 0);
        return totalPrice;
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void increasePromotionQuantity(int quantity) {
        this.promotionQuantity += quantity;
    }

    public boolean decreaseQuantity(int quantity) {
        if (this.quantity <= quantity) {
            return false;
        }
        this.quantity -= quantity;
        return true;
    }

    public boolean decreasePromotionCount(int quantity) {
        if (this.promotionQuantity < quantity) {
            return false;
        }
        this.promotionQuantity -= quantity;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.quantity > 0) {
            stringBuilder.append("-").append(" ");
            stringBuilder.append(this.name).append(" ");
            stringBuilder.append(this.price).append("원").append(" ");
            stringBuilder.append(this.quantity).append("개").append("\n");
        }
        if (this.promotionQuantity > 0) {
            stringBuilder.append("-").append(" ");
            stringBuilder.append(this.name).append(" ");
            stringBuilder.append(this.price).append("원").append(" ");
            stringBuilder.append(this.promotionQuantity).append("개").append(" ");
            stringBuilder.append(this.promotion).append("\n");
        }
        return stringBuilder.toString();
    }

    public Integer calcPromotionPrice(int buyCount) {
        return calcPromotionCount(buyCount) * this.price;
    }
}
