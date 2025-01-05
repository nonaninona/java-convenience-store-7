package store;

import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;

    public Cart(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void validateCart() {
        cartItemList.forEach(CartItem::validateCartItem);
    }

    public void printCart() {
        System.out.println("상품명\t수량\t금액");
        cartItemList.forEach(item -> {
            System.out.println(item);
        });
    }
}
