package christmas.model;

import java.util.List;

public record TotalPromotionOutcome(List<DiscountDetail> discounts, List<EventDetail> events, int totalBenefitAmount) {

    public record DiscountDetail(String name, int amount) {
    }

    public record EventDetail(String name, int amount) {

    }

    public int getGiveawayAmount() {
        return events.stream().filter(items -> items.name.equals("증정 이벤트"))
                .mapToInt(EventDetail::amount)
                .findFirst()
                .orElse(0);
    }
}
