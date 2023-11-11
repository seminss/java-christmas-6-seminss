package christmas.service;

import christmas.model.InitialOrderAmount;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.model.VisitDate;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {
    private VisitDate visitDate;
    private Order order;
    private InitialOrderAmount initialOrderAmount;

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

    public boolean checkEventQualification(){
        return initialOrderAmount.amount() > 10000;
    }
}
