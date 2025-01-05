package store;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

public class UserInputOutputHandler {
    public static List<Order> readProducts() {
        String input = Console.readLine();
        String[] orders = input.split(",");

        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            order = order.substring(1, order.length()-1);
            String[] tokens = order.split("-");
            orderList.add(new Order(tokens[0], Integer.parseInt(tokens[1])));
        }
        return orderList;
    }

    public static boolean readWantOrNot() {
        String input = Console.readLine();
        if(input.equals("Y"))
            return true;
        if(input.equals("N"))
            return false;
        throw new InputFormatException();
    }

    public static void printFreeCountQuestion(String productName, Integer freeCount) {
        System.out.println("현재 " + productName + "은(는) " + freeCount + "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
    }

    public static void printPromotionNotIncludedQuestion(String productName, Integer count) {
        System.out.println("현재 " + productName + " " + count + "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
    }

    public static void printMembershipQuestion() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
    }

    public static void printPrice(Integer price) {
        System.out.println("내실돈\t\t\t " + price);
    }
}
