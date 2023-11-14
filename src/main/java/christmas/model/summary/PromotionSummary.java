package christmas.model.summary;

import christmas.config.Badge;
import christmas.config.Giveaway;
import christmas.model.vo.DiscountAmount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.constant.EventSymbol.DATA_FORMAT;
import static christmas.model.policy.discount.DiscountSettings.GIVEAWAY_ITEM;

public record PromotionSummary(List<DiscountAmount> discounts, int totalDiscount, int finalPaymentAmount,
                               Badge eventBadge) {
    private static final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());

    public String giveawayItemDetails() {
        for (DiscountAmount discount : discounts) {
            if (discount.discountSettings() == GIVEAWAY_ITEM && discount.amount() != 0) {
                return String.format("%s %d개", Giveaway.CHAMPAGNE.getName(), Giveaway.CHAMPAGNE.getQuantity());
            }
        }
        return "없음";
    }

    public String discountDetails() {
        if (discounts.isEmpty() || discounts.stream().allMatch(d -> d.amount() == 0)) {
            return "없음";
        }
        return discounts.stream()
                .map(d -> String.format("%s: ", d.discountSettings().getDescription()) + df.format(d.amount()) + "원")
                .collect(Collectors.joining("\n"));
    }

    public String eventBadgeDetails() {
        return eventBadge.getName();
    }

    public String totalDiscountDetails() {
        return df.format(totalDiscount) + "원";
    }

    public String finalPaymentAmountDetails() {
        return df.format(finalPaymentAmount) + "원";
    }
}
