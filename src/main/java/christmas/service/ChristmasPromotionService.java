package christmas.service;

import christmas.model.discount.DiscountAmount;
import christmas.model.valueObject.InitialOrderAmount;
import christmas.model.order.Order;
import christmas.model.valueObject.VisitDate;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {
    private VisitDate visitDate;
    private Order order;
    private InitialOrderAmount initialOrderAmount;
    private DiscountAmount discountAmount;

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void createOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

    public boolean checkEventQualification() {
        return initialOrderAmount.amount() > 10000;
    }

    public void calculateDiscounts() {
    }
}