package store;

public class CartItem {
    private Product product;
    private Integer buyCount;

    public CartItem(Product product, Integer buyCount) {
        this.product = product;
        this.buyCount = buyCount;
    }

    public static CartItem from(Product product, Integer buyCount) {
        return new CartItem(product, buyCount);
    }

    public Product getProduct() {
        return product;
    }

    public Integer getBuyCount() {
        return buyCount;
    }
}
