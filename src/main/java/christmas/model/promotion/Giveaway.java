package christmas.model.promotion;

import christmas.model.menu.Menu;

public class Giveaway {
    private static final Menu giveaway = Menu.CHAMPAGNE;
    public static Menu getGiveaway() {
        return giveaway;
    }
}
