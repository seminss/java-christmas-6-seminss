package christmas.view.message;

public enum ValidationErrorMessage {
    INPUT_EMPTY("입력이 비었습니다."),
    INVALID_ORDER("유효하지 않은 주문입니다."),
    INPUT_NOT_INTEGER("입력이 정수가 아닙니다.");

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
