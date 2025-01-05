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

    public boolean checkCount(int buyCount) {
        if (this.promotionQuantity + this.quantity < buyCount) {
            System.out.println("재고가 없음");
            return false;
        }
        return true;
    }

    public Integer checkFreeCount(int buyCount) {
        if (buyCount <= this.promotionQuantity) {
            Integer freeCount = promotion.calcFreeCount(buyCount);
            if (freeCount != 0 && buyCount + freeCount <= this.promotionQuantity) {
                System.out.println(freeCount + "개를 공짜로 더 담을 수 있음");
                return freeCount;
            }
        }
        return 0;
    }

    public boolean checkPromotionCount(int buyCount) {
        if (buyCount > this.promotionQuantity) {
            Integer totalPromotionCount = promotion.calcTotalPromotionCount(this.promotionQuantity);
            Integer notPromotionCount = buyCount - totalPromotionCount;
            if (!promotion.isNoPromotion() && notPromotionCount != 0) {
                System.out.println(notPromotionCount + "개는 프로모션이 적용되지 않는데 괜춘?");
                return false;
            }
        }
        return true;
    }

    public Integer calcRawPrice(int buyCount) {
        return this.price * buyCount;
    }

    public Integer calcPrice(int buyCount) {
        if(buyCount <= this.promotionQuantity) {
            Integer promotionCount = promotion.calcPromotionCount(buyCount);
            System.out.println("buyCount : " + buyCount);
            System.out.println("promotionCount : " + promotionCount);
            Integer totalPrice = (buyCount - promotionCount) * this.price;

            this.promotionQuantity -= buyCount;
            return totalPrice;
        }

        Integer promotionCount = promotion.calcPromotionCount(this.promotionQuantity);
        System.out.println("buyCount : " + buyCount);
        System.out.println("promotionCount : " + promotionCount);
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
        return "\n" +
                "name : " + this.name + "\n" +
                "price : " + this.price + "\n" +
                "quantity : " + this.quantity + "\n" +
                "promotionQuantity : " + this.promotionQuantity + "\n" +
                "promotion : " + this.promotion + "\n";
    }
}
