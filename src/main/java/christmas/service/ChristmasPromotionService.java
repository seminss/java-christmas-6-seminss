package christmas.service;

import christmas.model.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {
    private VisitDate visitDate;
    private Order order;
    private InitialOrderAmount initialOrderAmount;
    private final Menu giveawayMenu = Menu.CHAMPAGNE;

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void createOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

    public void calculateInitialOrderAmount() {
        int amount = 0;
        for (Menu menu : Menu.values()) {
            int quantity = order.getOrder().getOrDefault(menu, 0);
            amount += quantity * menu.getPrice();
        }
        initialOrderAmount = new InitialOrderAmount(amount);
    }

    public int getInitialOrderAmount() {
        return initialOrderAmount.amount();
    }

    public boolean checkEventQualification() {
        return initialOrderAmount.amount() > 10000;
    }

    public boolean checkGiveawayEventQualification() { // TODO : 얘도 상태 정보로 저장 해야 할까??
        boolean dateCondition = visitDate.date() >= 1 && visitDate.date() <= 31;
        boolean amountCondition = initialOrderAmount.amount() > giveawayMenu.getPrice();
        return dateCondition && amountCondition;
    }
}
