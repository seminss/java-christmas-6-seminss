package christmas.view.input.parser;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.constant.EventSymbol.MENU_CONNECTOR;
import static christmas.constant.EventSymbol.MENU_SEPARATOR;

public class OrderParser {
    public static List<SimpleEntry<String, Integer>> parseEachMenu(String userInput) {
        return Arrays.stream(userInput.split(MENU_SEPARATOR.getValue()))
                .map(item->{
                    String[] parts = item.trim().split(MENU_CONNECTOR.getValue());
                    return new SimpleEntry<>(parts[0],Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }
}
