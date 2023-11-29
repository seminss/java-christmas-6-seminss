package christmas.service.util;

import christmas.model.TotalPromotionOutcome.DiscountDetail;
import christmas.model.TotalPromotionOutcome;
import christmas.model.Order;
import christmas.model.TotalPromotionOutcome.EventDetail;
import christmas.model.VisitDate;
import christmas.model.policy.discount.Discount;
import christmas.model.policy.event.Event;

import java.util.List;

public class BenefitCalculator {
    private final List<Discount> discounts;
    private final List<Event> events;

    public BenefitCalculator(List<Discount> discounts, List<Event> events) {
        this.discounts = discounts;
        this.events = events;
    }

    public TotalPromotionOutcome calculate(VisitDate visitDate, Order order) {
        List<DiscountDetail> discountDetails = calculateDiscounts(discounts, order, visitDate);
        List<EventDetail> eventDetails = calculateEvents(events, order, visitDate);
        System.out.println(eventDetails);

        return new TotalPromotionOutcome(
                discountDetails, eventDetails, getTotalBenefitAmount(discountDetails, eventDetails));
    }

    private int getTotalBenefitAmount(List<DiscountDetail> discountDetails, List<EventDetail> eventDetails) {
        int totalDiscounts = discountDetails.stream().mapToInt(DiscountDetail::amount).sum();
        int totalEvents = eventDetails.stream().mapToInt(EventDetail::amount).sum();
        return totalDiscounts + totalEvents;
    }

    private List<DiscountDetail> calculateDiscounts(List<Discount> policies, Order order, VisitDate visitDate) {
        return policies.stream()
                .map(discount -> new DiscountDetail(discount.getName(), discount.calculateBenefit(order, visitDate)))
                .filter(discountDetail -> discountDetail.amount() < 0)
                .toList();
    }

    private List<EventDetail> calculateEvents(List<Event> policies, Order order, VisitDate visitDate) {
        return policies.stream()
                .map(event -> new EventDetail(event.getName(), event.calculateBenefit(order, visitDate)))
                .filter(eventDetail -> eventDetail.amount() < 0)
                .toList();
    }
}
