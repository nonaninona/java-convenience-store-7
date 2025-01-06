package store;

import store.promotion.Promotion;

public class Product {
    private String name;
    private Integer price;
    private Quantity quantity;
    private Promotion promotion;

    public Product(String name, Integer price, Quantity quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public void fill(Quantity quantity) {
        this.quantity.add(quantity);
    }

    public void buy(int buyCount) {
        this.quantity.minus(buyCount);
    }

    public String toSellInfoString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-").append(" ");
        stringBuilder.append(this.name).append(" ");
        stringBuilder.append(UserInputOutputHandler.formatMoney(this.price)).append("원").append(" ");
        if (this.quantity.isPromotionQuantityGreaterThan(0)) {
            stringBuilder.append(this.quantity.getPromotionQuantity()).append("개").append(" ");
            stringBuilder.append(this.promotion).append("\n");
        } else {
            stringBuilder.append("재고 없음").append("\n");
        }

        stringBuilder.append("-").append(" ");
        stringBuilder.append(this.name).append(" ");
        stringBuilder.append(UserInputOutputHandler.formatMoney(this.price)).append("원").append(" ");
        if (this.quantity.isBasicQuantityGreaterThan(0)) {
            stringBuilder.append(this.quantity.getBasicQuantity()).append("개").append("\n");
        } else {
            stringBuilder.append("재고 없음").append("\n");
        }
        return stringBuilder.toString();
    }

    public void validateQuantity(int buyCount) {
        this.quantity.validateIfCanMinusCount(buyCount);
    }

    public Integer checkFreeQuantity(int buyCount) {
        return promotion.calcFreeCount(buyCount, this.quantity);
    }

    public Integer checkNotIncludedPromotionCount(int buyCount) {
        return promotion.calcNotIncludedPromotionCount(buyCount, this.quantity);
    }

    public String getName() {
        return name;
    }

    public Integer calcPrice(int buyCount) {
        return this.price * buyCount;
    }

    public Integer calcPromotedPrice(int buyCount) {
        Integer promotedCount = calcPromotedCount(buyCount);
        return promotedCount * this.price;
    }

    public Integer calcPromotedCount(int buyCount) {
        return promotion.calcPromotionCount(new Quantity(this.quantity.getBasicQuantity(), buyCount));
    }
}
