package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.parser.VisitDateParser;
import christmas.view.input.parser.OrderParser;
import christmas.view.input.validator.OrderMenuValidator;
import christmas.view.input.validator.VisitDateValidator;

import java.util.AbstractMap;
import java.util.List;

public class InputView {

    private InputView() {

    }

    public static Integer readVisitDate(){
        String userInput = read();
        VisitDateValidator.validate(userInput);
        return VisitDateParser.parseInteger(userInput);
    }

    public static List<AbstractMap.SimpleEntry<String, Integer>> readOrder(){
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