package christmas.model;

import christmas.config.Badge;
import christmas.config.Menu;
import christmas.model.vo.DiscountAmount;

import java.util.List;
import java.util.Optional;

public record PromotionSummary(Optional<Menu> giveawayItem,
                               List<DiscountAmount> discounts,
                               int totalDiscount,
                               int finalPaymentAmount,
                               Badge eventBadge) {
}
