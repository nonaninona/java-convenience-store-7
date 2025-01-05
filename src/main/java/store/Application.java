package store;

import java.io.IOException;
import java.util.List;

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
            List<Order> orderList = UserInputOutputHandler.readProducts();
            orderList = stock.validateOrder(orderList);

            Integer rawPrice = stock.calcRawriace(orderList);
            Integer discountPrice = MemberShipDiscount.calcDiscountPrice(rawPrice);
            Integer price = stock.calcPrice(orderList);

            UserInputOutputHandler.printMembershipQuestion();
            boolean isMemberShip = UserInputOutputHandler.readWantOrNot();
            if(isMemberShip)
                price -= discountPrice;
            UserInputOutputHandler.printPrice(price);
        } catch (StockExceedException e) {
            System.out.println("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }

//        List<Product> cart = new ArrayList<>();
//        List<Integer> buyCounts = new ArrayList<>();
//        productMap.forEach((name, product) -> {
////            if(!name.equals("사이다"))
////                return;
//            System.out.println(product);
//            Integer buyCount = Randoms.pickNumberInRange(1, 10);
////            Integer buyCount = 2;
//            System.out.println("구매 : " + buyCount);
//
//            if(!product.checkCount(buyCount))
//                return;
//
//            Integer freeCount = product.checkFreeCount(buyCount);
//            if(freeCount > 0) {
//                Integer buyOrNot = Randoms.pickNumberInRange(0, 1);
//                boolean isBuy = buyOrNot == 1;
//                System.out.println("구매결정 : " + isBuy);
//                buyCount += buyOrNot * freeCount;
//            }
//
//            if(!product.checkPromotionCount(buyCount)) {
//                Integer buyOrNot = Randoms.pickNumberInRange(0, 2);
//                boolean isBuy = buyOrNot == 1;
//                System.out.println("구매결정 : " + isBuy);
//                if(!isBuy)
//                    return;
//            }
//
//            cart.add(product);
//            buyCounts.add(buyCount);
//        });
//
//        Integer rawTotalPrice = 0;
//        for (int i = 0; i < cart.size(); i++) {
//            Product product = cart.get(i);
//            Integer buyCount = buyCounts.get(i);
//
//            System.out.println(product);
//            Integer price = product.calcRawPrice(buyCount);
//            System.out.println("가격 : " + price);
//            rawTotalPrice += price;
//        }
//        System.out.println("rawTotalPrice : " + rawTotalPrice);
//
//        System.out.println("=".repeat(20));
//        System.out.println(cart);
//        System.out.println(buyCounts);
//
//        Integer totalPrice = 0;
//        for (int i = 0; i < cart.size(); i++) {
//            Product product = cart.get(i);
//            Integer buyCount = buyCounts.get(i);
//
//            System.out.println(product);
//            Integer price = product.calcPrice(buyCount);
//            System.out.println("가격 : " + price);
//            System.out.println(product);
//            totalPrice += price;
//        }
//        System.out.println("totalPrice : " + totalPrice);
//
//        Integer buyOrNot = Randoms.pickNumberInRange(0, 1);
//        boolean isBuy = buyOrNot == 1;
//        System.out.println("멤버쉽 여부 : " + isBuy);
//        if(isBuy)
//            totalPrice -= MemberShipDiscount.calcDiscount(rawTotalPrice);
//        System.out.println("totalPrice : " + totalPrice);
    }
}
