package store;

public class StockExceedException extends RuntimeException {
    public StockExceedException() {
        super("재고 없음");
    }
}
