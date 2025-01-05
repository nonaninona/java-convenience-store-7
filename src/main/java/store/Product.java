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

    public void increaseCount(int count) {
        this.quantity+=count;
    }

    public void increasePromotionCount(int count) {
        this.promotionQuantity+=count;
    }

    public boolean decreaseCount(int count) {
        if(this.quantity <= count)
            return false;
        this.quantity-=count;
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
