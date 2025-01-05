package store;


import java.time.LocalDate;

public class Promotion {
    private String name;
    private Integer buy;
    private Integer get;
    private LocalDate startDate;
    private LocalDate endDate;

    public static Promotion noPromotion = new Promotion("", 0, 0, LocalDate.MIN, LocalDate.MAX);

    public Promotion(String name, Integer buy, Integer get, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.get = get;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    boolean isNoPromotion() {
        return this.name.isEmpty();
    }

    public Integer calcPromotionCount(int count) {
        if(buy+get == 0)
            return 0;
        return count/(buy+get);
    }

    public Integer calcFreeCount(int count) {
        if(buy+get == 0)
            return 0;
        Integer leftCount = count%(buy+get);
        if(leftCount < buy)
            return 0;
        Integer freeCount = get - (leftCount - buy);
        return freeCount;
    }

    public Integer calcTotalPromotionCount(int count) {
        return calcPromotionCount(count) * (buy+get);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
