package store;

import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;

    public Cart(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void checkCartItem() {
        cartItemList.forEach(CartItem::checkCartItem);
        validateCartItem();
    }

    private void validateCartItem() {
        int i = 0;
        while(i < cartItemList.size()) {
            CartItem item = cartItemList.get(i);
            if(item.isNotBuying()) {
                cartItemList.remove(i);
                continue;
            }
            i++;
        }
    }

    public void printCartItemList() {
        System.out.println("상품명\t수량\t금액");
        cartItemList.forEach(System.out::println);
        System.out.println("=".repeat(20));
        cartItemList.stream()
                .filter(item -> item.calcPromotionCount() > 0)
                .toList()
                .forEach(System.out::println);
        System.out.println("=".repeat(20));
        System.out.println("총 구매액\t"+sumBuyCount()+"\t"+calcRawPrice());
        System.out.println("행사 할인\t"+"\t"+-1*calcPromotionPrice());
        System.out.println("내실돈\t"+sumBuyCount()+"\t"+calcPrice());

    }

    private Integer sumBuyCount() {
        return cartItemList.stream()
                .mapToInt(CartItem::getBuyCount)
                .sum();
    }

    private Integer calcPromotionPrice() {
        return cartItemList.stream().mapToInt(CartItem::calcPromotionCount).sum();
    }

    private Integer calcRawPrice() {
        return cartItemList.stream()
                .mapToInt(CartItem::calcRawPrice)
                .sum();
    }

    private Integer calcPrice() {
        return cartItemList.stream()
                .mapToInt(CartItem::calcPrice)
                .sum();
    }
}
