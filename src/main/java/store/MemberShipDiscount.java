package store;

public class MemberShipDiscount {
    public static Integer calcDiscountPrice(Integer totalPrice) {
        return Integer.min((int) (totalPrice * 0.3), 8000);
    }
}
