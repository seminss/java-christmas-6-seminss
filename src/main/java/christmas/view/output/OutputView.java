package christmas.view.output;

import static christmas.view.output.PromptMessage.*;

public class OutputView {
    public static void printIntroductionMessage() {
        printMessage(INTRODUCTION.getMessage());
    }

    public static void printTakeOrderMessage() {
        printMessage(TAKE_ORDER.getMessage());
    }

    public static void printTakeDateMessage() {
        printMessage(TAKE_VISIT_DATE.getMessage());
    }

    public static void printPromotionPreviewMessage(DetailsFormatter visitDateMessage) {
        printMessage(visitDateMessage.toString());
    }

    public static void printFormattedDiscountResultMessage(DetailsFormatter discountResultMessage) {
        printMessage(discountResultMessage.toString());
    }

    public static void printFormattedOrderMessage(DetailsFormatter orderMessage) {
        printMessage(orderMessage.toString());
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }


}
