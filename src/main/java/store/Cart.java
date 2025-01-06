package store;

import java.util.List;

public class Cart {
    private List<CartItem> cartItemList;
    boolean isMemberShip = false;
    private final Double MEMBERSHIP_DISCOUNT_RATE = 0.3;
    private final Integer MEMBERSHIP_MAXIMUM_DISCOUNT_PRICE = 8000;

    public Cart(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public void validateCartItem() {
        cartItemList.forEach(CartItem::checkCartItem);
        removeNotBuyingItem();
    }

    private void removeNotBuyingItem() {
        int i = 0;
        while (i < cartItemList.size()) {
            CartItem item = cartItemList.get(i);
            if (item.isNotBuying()) {
                cartItemList.remove(i);
                continue;
            }
            i++;
        }
    }

    public void checkMemberShip() {
        UserInputOutputHandler.printMembershipQuestion();
        isMemberShip = UserInputOutputHandler.readWantOrNot();
    }

    private Integer calcTotalPrice() {
        return cartItemList.stream()
                .mapToInt(CartItem::calcPrice)
                .sum();
    }

    private Integer calcTotalPromotedPrice() {
        return cartItemList.stream()
                .mapToInt(CartItem::calcPromotedPrice)
                .sum();
    }

    private Integer calcMemberShipDiscountPrice() {
        return Math.min((int) (calcTotalPrice() * MEMBERSHIP_DISCOUNT_RATE), MEMBERSHIP_MAXIMUM_DISCOUNT_PRICE);
    }

    public String buy() {
        String bill = makeBill();
        cartItemList.forEach(CartItem::buy);
        return bill;
    }

    private String makeBill() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=".repeat(10)).append("W 편의점").append("=".repeat(10)).append("\n");
        stringBuilder.append("상품명\t\t수량\t\t금액").append("\n");
        cartItemList.forEach(item -> stringBuilder.append(item.toBillListString()).append("\n"));
        stringBuilder.append("=".repeat(10)).append("증 정").append("=".repeat(10)).append("\n");
        cartItemList.forEach(item -> stringBuilder.append(item.toPromotedListString()).append("\n"));
        stringBuilder.append("=".repeat(20)).append("\n");
        stringBuilder.append("총 구매액\t\t").append(sumBuyCount()).append("\t\t").append(calcTotalPrice()).append("\n");
        stringBuilder.append("행사 할인\t\t\t\t").append(-1 * calcTotalPromotedPrice()).append("\n");
        stringBuilder.append("멤버십 할인\t\t\t").append(-1 * calcMemberShipDiscountPrice()).append("\n");
        stringBuilder.append("내실돈\t\t\t\t").append(calcTotalPayingPrice());
        return stringBuilder.toString();
    }

    private Integer calcTotalPayingPrice() {
        Integer price = calcTotalPrice() - calcTotalPromotedPrice();
        if(isMemberShip)
            price -= calcMemberShipDiscountPrice();
        return price;
    }

    private Integer sumBuyCount() {
        return cartItemList.stream()
                .mapToInt(CartItem::getBuyCount)
                .sum();
    }
}
