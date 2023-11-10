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

    public static void printResultMessage() {
        printMessage(RESULT_PREVIEW.getMessage());
    }

    private static void printMessage() {
        System.out.println();
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
