package christmas.service;

import christmas.model.Order;
import christmas.model.TotalPromotionOutcome;
import christmas.model.VisitDate;
import christmas.model.policy.discount.DdayDiscount;
import christmas.model.policy.discount.SpecialDiscount;
import christmas.model.policy.discount.WeekdayDiscount;
import christmas.model.policy.discount.WeekendDiscount;
import christmas.model.policy.event.GiveawayEvent;
import christmas.dto.response.PromotionResponse;
import christmas.dto.request.DateRequest;
import christmas.dto.request.OrderRequest;
import christmas.service.util.BenefitCalculator;
import christmas.service.util.ResponseBuilder;

import java.util.List;

public class ChristmasPromotionService {
    BenefitCalculator benefitCalculator = new BenefitCalculator(
            List.of(new DdayDiscount(), new SpecialDiscount(), new WeekdayDiscount(), new WeekendDiscount()),
            List.of(new GiveawayEvent())
    );

    public PromotionResponse getPromotionSummary(DateRequest dateRequest, OrderRequest orderRequest) {
        Order order = Order.of(orderRequest);
        VisitDate visitDate = VisitDate.of(dateRequest);
        TotalPromotionOutcome promotionOutcome = benefitCalculator.calculate(visitDate, order);
        return ResponseBuilder.build(order, visitDate, promotionOutcome);
    }
}
