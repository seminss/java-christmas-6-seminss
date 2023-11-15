package christmas.service;

import christmas.model.*;
import christmas.model.summary.OrderSummary;
import christmas.model.summary.PromotionSummary;
import christmas.model.summary.VisitDateSummary;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Optional;

import static christmas.constant.EventThreshold.PROMOTION_THRESHOLD;

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

    public OrderSummary getOrderSummary() {
        return new OrderSummary(order);
    }

    public VisitDateSummary getVisitDateSummary() {
        return new VisitDateSummary(visitDate);
    }

    public PromotionSummary getPromotionSummary() {
        if (canReceivePromotion()) {
            DiscountedItems discountedItems = discountCalculator.calculateDiscounts(visitDate, order);
            DiscountResults discountResults = new DiscountResults(order.getBaseOrderAmount(), discountedItems);
            return new PromotionSummary(discountedItems.items(), discountResults.getTotalDiscountAmount(),
                    discountResults.getFinalPaymentAmount(), Optional.ofNullable(discountResults.getBadge()));
        }
        return new PromotionSummary(order.getBaseOrderAmount());
    }

    private boolean canReceivePromotion() {
        return order.getBaseOrderAmount() > PROMOTION_THRESHOLD.getAmount();
    }

}
