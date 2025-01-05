package store;

import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;

    public Cart(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Integer calcRawPrice(List<CartItem> cartItemList) {
        return cartItemList.stream()
                .mapToInt(item -> item.getProduct().calcRawPrice(item.getBuyCount()))
                .sum();
    }

    public Integer calcPrice(List<CartItem> cartItemList) {
        return cartItemList.stream()
                .mapToInt(item -> item.getProduct().calcPrice(item.getBuyCount()))
                .sum();
    }
}
