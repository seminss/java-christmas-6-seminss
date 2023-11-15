package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.parser.VisitDateParser;
import christmas.view.input.parser.OrderParser;
import christmas.view.input.validator.OrderMenuValidator;
import christmas.view.input.validator.VisitDateValidator;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.function.Supplier;

public class InputView {

    public static <T> T getValidInput(Supplier<T> inputSupplier) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Integer readVisitDate() {
        String userInput = read();
        VisitDateValidator.validate(userInput);
        return VisitDateParser.parseInteger(userInput);
    }

    public static List<SimpleEntry<String, Integer>> readOrder() {
        String userInput = read();
        OrderMenuValidator.validate(userInput);
        return OrderParser.parseEachMenu(userInput);
    }

    public static void readClose() {
        Console.close();
    }

    private static String read() {
        return Console.readLine();
    }
}