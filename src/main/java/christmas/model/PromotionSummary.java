package christmas.model;

import christmas.config.Badge;
import christmas.config.Giveaway;
import christmas.model.vo.DiscountAmount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.constant.EventSymbol.DATA_FORMAT;
import static christmas.model.policy.discount.DiscountConfig.GIVEAWAY_ITEM;

public record PromotionSummary(List<DiscountAmount> discounts,
                               int totalDiscount,
                               int finalPaymentAmount,
                               Badge eventBadge) {
    private static final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());

    public String formattedGiveawayItem() {
        for (DiscountAmount discount : discounts) {
            if (discount.discountConfig() == GIVEAWAY_ITEM && discount.amount() != 0) {
                return String.format("%s %d개", Giveaway.CHAMPAGNE.getName(), Giveaway.CHAMPAGNE.getQuantity());
            }
        }
        return "없음";
    }

    public String formattedDiscountDetails() {
        if (discounts.isEmpty() || discounts.stream().allMatch(d -> d.amount() == 0)) {
            return "없음";
        }
        return discounts.stream()
                .map(d -> String.format("%s: ", d.discountConfig().getDescription()) + df.format(d.amount()) + "원")
                .collect(Collectors.joining("\n"));
    }

    public String formattedEventBadge() {
        return eventBadge.getName();
    }

    public String formattedTotalDiscount() {
        return df.format(totalDiscount) + "원";
    }

    public String formattedFinalPaymentAmount() {
        return df.format(finalPaymentAmount) + "원";
    }
}
