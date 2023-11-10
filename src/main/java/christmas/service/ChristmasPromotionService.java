package christmas.service;

import christmas.model.Order;
import christmas.model.VisitDate;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {
    private VisitDate visitDate;
    private Order order;

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void createOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

}
