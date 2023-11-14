package christmas.service;

import christmas.model.*;
import christmas.model.summary.PromotionSummary;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {

    Order order;
    VisitDate visitDate;
    ChristmasDiscountCalculator discountCalculator = new ChristmasDiscountCalculator();

    public void setVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void setOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

    public Order getOrder() {
        return order;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public PromotionSummary getPromotionSummary() {
        DiscountedItems discountedItems = discountCalculator.calculateDiscounts(visitDate, order);
        OrderDiscountDetails discountDetails = new OrderDiscountDetails(order.calculateTotalOrderAmount(), discountedItems);
        return new PromotionSummary(discountedItems.discounts(), discountDetails.getTotalDiscountAmount(),
                discountDetails.getFinalPaymentAmount(), discountDetails.getBadge());
    }

}
    /* public boolean checkEventQualification() {
        return order.getInitialOrderAmount() > 10000;
    }**/
