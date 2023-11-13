package christmas.service;

import christmas.model.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionService {

    Order order;
    VisitDate visitDate;
    ChristmasDiscountCalculator discountCalculator = new ChristmasDiscountCalculator();

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }

    public void createOrder(List<SimpleEntry<String, Integer>> readOrder) {
        order = new Order(readOrder);
    }

    public Order getOrder() {
        return order;
    }

    public PromotionSummary getPromotionSummary() {
        DiscountBenefits benefits = discountCalculator.calculateDiscounts(visitDate, order);
        DiscountResult result = new DiscountResult(order.getInitialOrderAmount(), benefits.getTotalDiscount());
        return new PromotionSummary(benefits.getGiveawayItem(), benefits.getDiscounts()
                , benefits.getTotalDiscount(), result.getFinalPaymentAmount(), result.getBadge());
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    /* public boolean checkEventQualification() {
        return order.getInitialOrderAmount() > 10000;
    }**/


}