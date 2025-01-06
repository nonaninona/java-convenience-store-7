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
            List<Order> orderList = UserInputOutputHandler.readOrders(stock.productsToString());
            List<CartItem> cartItemList = stock.makeCartItems(orderList);

            Cart cart = new Cart(cartItemList);
            cart.validateCartItem();
            cart.checkMemberShip();
            String bill = cart.buy();
            UserInputOutputHandler.printBill(bill);
        } catch (StockExceedException e) {
            System.out.println(e.getMessage());
        } catch (InputFormatException e) {
            System.out.printf(e.getMessage());
        }
    }
}
