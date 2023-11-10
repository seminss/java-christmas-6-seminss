package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.input.parser.VisitDateParser;
import christmas.view.input.validator.VisitDateValidator;

public class InputView {

    private InputView() {

    }

    public static Integer readVisitDate(){
        String userInput = read();
        VisitDateValidator.validate(userInput);
        return VisitDateParser.parseInteger(userInput);
    }

/*    public static EnumMap<Menu,Integer> readOrder(){
        String userInput = read();
        OrderMenuValidator.validate(userInput);
    }*/

    public static void readClose() {
        Console.close();
    }

    private static String read() {
        return Console.readLine();
    }


}