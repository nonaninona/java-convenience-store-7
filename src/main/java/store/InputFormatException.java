package store;

public class InputFormatException extends RuntimeException {
    public InputFormatException() {
        super("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
    }
}
