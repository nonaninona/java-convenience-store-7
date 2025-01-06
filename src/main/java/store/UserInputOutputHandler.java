package store;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import store.exception.InputFormatException;

public class UserInputOutputHandler {
    public static List<Order> readOrders(String productsList) {
        System.out.print(productsList);

        String input = Console.readLine();
        String[] orders = input.split(",");

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            order = order.substring(1, order.length() - 1);
            String[] tokens = order.split("-");
            orderList.add(new Order(tokens[0], Integer.parseInt(tokens[1])));
        }
        return orderList;
    }

    public static boolean readWantOrNot() {
        String input = Console.readLine();
        if (input.equals("Y")) {
            return true;
        }
        if (input.equals("N")) {
            return false;
        }
        throw new InputFormatException();
    }

    public static void printFreeCountQuestion(Product product, Integer freeCount) {
        System.out.println("현재 " + product.getName() + "은(는) " + freeCount + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
    }

    public static void printPromotionNotIncludedQuestion(Product product, Integer count) {
        System.out.println("현재 " + product.getName() + " " + count + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
    }

    public static void printMembershipQuestion() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    public static void printBill(String bill) {
        System.out.println(bill);
    }

    public static void printRepeatQuestion() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
    }
}
