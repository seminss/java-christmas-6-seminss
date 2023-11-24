package christmas.service;

import christmas.model.*;
import christmas.model.summary.PromotionSummary;
import christmas.view.input.DateRequest;
import christmas.view.input.OrderRequest;

public class ChristmasPromotionService {
    ChristmasDiscountCalculator discountCalculator = new ChristmasDiscountCalculator();

    public PromotionSummary getPromotionSummary(DateRequest dateRequest, OrderRequest orderRequest) {
        Order order = Order.of(orderRequest);
        VisitDate visitDate = VisitDate.of(dateRequest);
        if (order.canReceivePromotion()) {
            DiscountedItems discountedItems = discountCalculator.calculateDiscounts(visitDate, order);
            DiscountResults discountResults = new DiscountResults(order.getBaseOrderAmount(), discountedItems);
            return new PromotionSummary(discountedItems.items(), discountResults.getTotalDiscountAmount(),
                    discountResults.getFinalPaymentAmount(), discountResults.getBadge());
        }
        return new PromotionSummary(order.getBaseOrderAmount());
    }

}
