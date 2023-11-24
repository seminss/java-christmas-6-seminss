package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private InputView() {
    }

    public static DateRequest readVisitDate() {
        String userInput = read();
        return DateRequest.of(userInput);
    }

    public static OrderRequest readOrder() {
        String userInput = read();
        return OrderRequest.of(userInput);
    }

    public static void readClose() {
        Console.close();
    }

    private static String read() {
        return Console.readLine();
    }
}