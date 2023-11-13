package christmas.service;

import christmas.model.discount.DiscountResult;
import christmas.model.order.Order;
import christmas.model.vo.VisitDate;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {

    Order order;
    VisitDate visitDate;
    ChristmasDiscountCalculator discountCalculator;


    public ChristmasPromotionService() {

    }

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void createOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

    /* public boolean checkEventQualification() {
        return order.getInitialOrderAmount() > 10000;
    }**/

    public DiscountResult generateDiscountResult() {
        return discountCalculator.calculateDiscounts(visitDate, order);
    }
}