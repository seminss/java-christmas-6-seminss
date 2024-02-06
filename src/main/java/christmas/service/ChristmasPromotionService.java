package christmas.service;

import christmas.model.Order;
import christmas.model.TotalPromotionOutcome;
import christmas.model.VisitDate;
import christmas.dto.response.PromotionResponse;
import christmas.dto.request.DateRequest;
import christmas.dto.request.OrderRequest;
import christmas.service.util.BenefitCalculator;
import christmas.service.util.ResponseBuilder;

public class ChristmasPromotionService {
    BenefitCalculator benefitCalculator;

    public ChristmasPromotionService(BenefitCalculator benefitCalculator) {
        this.benefitCalculator = benefitCalculator;
    }

    public PromotionResponse getPromotionSummary(DateRequest dateRequest, OrderRequest orderRequest) {
        Order order = Order.of(orderRequest);
        VisitDate visitDate = VisitDate.of(dateRequest);
        TotalPromotionOutcome promotionOutcome = benefitCalculator.calculate(visitDate, order);
        return ResponseBuilder.build(order, visitDate, promotionOutcome);
    }
}
