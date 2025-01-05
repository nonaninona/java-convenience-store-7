package store;

import java.io.IOException;
import java.util.List;
import store.exception.InputFormatException;
import store.exception.StockExceedException;

public class Application {
    public static void main(String[] args) {
        Stock stock;
        try {
            stock = new Stock();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            stock.printStocks();
            List<Order> orderList = UserInputOutputHandler.readOrders();
            List<CartItem> cartItemList = stock.makeCartItems(orderList);

            Cart cart = new Cart(cartItemList);
            cart.checkCartItem();

            cart.printCartItemList();

//            Integer rawPrice = cart.calcRawPrice(cartItemList);
//            Integer discountPrice = MemberShipDiscount.calcDiscountPrice(rawPrice);
//            Integer price = cart.calcPrice(cartItemList);
//
//            UserInputOutputHandler.printMembershipQuestion();
//            boolean isMemberShip = UserInputOutputHandler.readWantOrNot();
//            if (isMemberShip) {
//                price -= discountPrice;
//            }
//            UserInputOutputHandler.printPrice(price);
        } catch (StockExceedException e) {
            System.out.println(e.getMessage());
        } catch (InputFormatException e) {
            System.out.printf(e.getMessage());
        }
    }
}
