package christmas.service;

import christmas.model.*;
import christmas.model.summary.PromotionSummary;

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

    public Order getOrder() {
        return order;
    }

    public VisitDate getVisitDate() {
        return visitDate;
    }

    public PromotionSummary getPromotionSummary() {
        if (order.calculateBaseOrderAmount() > PROMOTION_THRESHOLD.getAmount()) {
            DiscountedItems discountedItems = discountCalculator.calculateDiscounts(visitDate, order);
            OrderDiscountDetails discountDetails = new OrderDiscountDetails(order.calculateBaseOrderAmount(), discountedItems);
            return new PromotionSummary(discountedItems.items(), discountDetails.getTotalDiscountAmount(),
                    discountDetails.getFinalPaymentAmount(), Optional.ofNullable(discountDetails.getBadge()));
        }
        return new PromotionSummary(order.calculateBaseOrderAmount());
    }

}
