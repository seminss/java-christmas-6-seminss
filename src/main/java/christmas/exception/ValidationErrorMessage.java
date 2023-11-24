package christmas.exception;

public enum ValidationErrorMessage {
    INVALID_ORDER("유효하지 않은 주문입니다."),
    INVALID_DATE("유효하지 않은 날짜입니다."),
    CAN_NOT_DRINK_ONLY("음료만 주문 시, 주문할 수 없습니다.");


    private final String message;

    ValidationErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        String errorMessage = "[ERROR] ";
        String retryMessage = " 다시 입력해 주세요.";
        return errorMessage + message + retryMessage;
    }
}
