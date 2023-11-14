package christmas.model.summary;

import christmas.config.Badge;
import christmas.config.Giveaway;
import christmas.model.vo.DiscountAmount;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static christmas.constant.EventSymbol.DATA_FORMAT;
import static christmas.model.policy.discount.DiscountSettings.GIVEAWAY_DISCOUNT;

public final class PromotionSummary {
    private static final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());
    private static final String DEFAULT_MESSAGE = "없음";
    private static final String GIVEAWAY_FORMAT = "%s %d개";
    private static final String DISCOUNT_AMOUNT_FORMAT = "%s: %s";
    private static final int NO_DISCOUNT_THRESHOLD = 0;
    private static final Giveaway GIVEAWAY_ITEM = Giveaway.CHAMPAGNE;

    private String discountDetails;
    private String giveawayDetails;
    private String totalDiscountAmountDetails;
    private String finalPaymentAmountDetails;
    private String eventBadgeDetails;

    public PromotionSummary(int finalPaymentAmount) {
        this.discountDetails = DEFAULT_MESSAGE;
        this.giveawayDetails = DEFAULT_MESSAGE;
        this.totalDiscountAmountDetails = df.format(0);
        this.finalPaymentAmountDetails = df.format(finalPaymentAmount);
        this.eventBadgeDetails = DEFAULT_MESSAGE;
    }

    public PromotionSummary(List<DiscountAmount> discounts, int totalDiscount, int finalPaymentAmount,
                            Optional<Badge> eventBadge) {
        initDiscountDetails(discounts);
        initFinalPaymentAmountDetails(finalPaymentAmount);
        initTotalDiscountDetails(totalDiscount);
        initEventBadgeDetails(eventBadge);
        initGiveawayItemDetails(discounts);
    }

    public String getDiscountDetails() {
        return discountDetails;
    }

    public String getGiveawayDetails() {
        return giveawayDetails;
    }

    public String getTotalDiscountAmountDetails() {
        return totalDiscountAmountDetails;
    }

    public String getFinalPaymentAmountDetails() {
        return finalPaymentAmountDetails;
    }

    public String getEventBadgeDetails() {
        return eventBadgeDetails;
    }

    private void initGiveawayItemDetails(List<DiscountAmount> discounts) {
        this.giveawayDetails = discounts.stream()
                .filter(d -> d.discountSettings() == GIVEAWAY_DISCOUNT && d.amount() > NO_DISCOUNT_THRESHOLD)
                .findFirst()
                .map(d -> String.format(GIVEAWAY_FORMAT, GIVEAWAY_ITEM.getName(), GIVEAWAY_ITEM.getPrice()))
                .orElse(DEFAULT_MESSAGE);
    }

    private void initDiscountDetails(List<DiscountAmount> discounts) {
        if (discounts.stream().allMatch(d -> d.amount() == NO_DISCOUNT_THRESHOLD)) {
            this.discountDetails = DEFAULT_MESSAGE;
            return;
        }
        this.discountDetails = discounts.stream()
                .filter(d -> d.amount() < NO_DISCOUNT_THRESHOLD)
                .map(this::formatDiscountDetail)
                .collect(Collectors.joining("\n"));
    }

    private void initEventBadgeDetails(Optional<Badge> eventBadge) {
        if (eventBadge.isEmpty()) {
            this.eventBadgeDetails = DEFAULT_MESSAGE;
            return;
        }
        this.eventBadgeDetails = eventBadge.get().getName();
    }

    private void initTotalDiscountDetails(int totalDiscount) {
        this.totalDiscountAmountDetails = df.format(totalDiscount);
    }

    private void initFinalPaymentAmountDetails(int finalPaymentAmount) {
        this.finalPaymentAmountDetails = df.format(finalPaymentAmount);
    }

    private String formatDiscountDetail(DiscountAmount discount) {
        return String.format(DISCOUNT_AMOUNT_FORMAT,
                discount.discountSettings().getDescription(),
                df.format(discount.amount()));
    }

}
