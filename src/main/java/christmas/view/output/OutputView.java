package christmas.view.output;

import static christmas.view.output.PromptMessage.*;

public class OutputView {
    public static void printIntroductionMessage() {
        printMessage(INTRODUCTION_MESSAGE.getMessage());
    }

    public static void printTakeOrderMessage() {
        printMessage(TAKE_ORDER_MESSAGE.getMessage());
    }

    public static void printTakeDateMessage() {
        printMessage(TAKE_VISIT_DATE_MESSAGE.getMessage());
    }

    public static void printMessage(DetailsFormatter detailsFormatter) {
        printMessage(detailsFormatter.toString());
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

}
