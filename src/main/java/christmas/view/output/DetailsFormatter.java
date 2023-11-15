package christmas.view.output;

import christmas.model.summary.OrderSummary;
import christmas.model.summary.PromotionSummary;
import christmas.model.summary.VisitDateSummary;

import static christmas.view.output.PromptMessage.*;

public class DetailsFormatter {
    private final StringBuilder sb = new StringBuilder();

    public static DetailsFormatter visitDateFormatter(VisitDateSummary summary) {
        return new DetailsFormatter(summary);
    }

    public static DetailsFormatter OrderFormatter(OrderSummary summary) {
        return new DetailsFormatter(summary);
    }

    public static DetailsFormatter DiscountResultFormatter(PromotionSummary summary) {
        return new DetailsFormatter(summary);
    }

    public DetailsFormatter(VisitDateSummary summary) {
        appendLine(summary.getVisitDateDetails());
    }

    public DetailsFormatter(OrderSummary summary) {
        appendLine(ORDER_HEADER.getMessage())
                .appendLine(summary.getOrderDetails(), 2);
        appendLine(TOTAL_ORDER_AMOUNT_HEADER.getMessage())
                .appendLine(summary.getBaseOrderAmountDetails());
    }

    public DetailsFormatter(PromotionSummary summary) {
        appendLine(GIVEAWAY_HEADER.getMessage())
                .appendLine(summary.getGiveawayDetails(), 2)
                .appendLine(BENEFIT_DETAILS_HEADER.getMessage())
                .appendLine(summary.getDiscountDetails(), 2)
                .appendLine(TOTAL_DISCOUNT_AMOUNT_HEADER.getMessage())
                .appendLine(summary.getTotalDiscountAmountDetails(), 2)
                .appendLine(FINAL_PAYMENT_AMOUNT_HEADER.getMessage())
                .appendLine(summary.getFinalPaymentAmountDetails(), 2)
                .appendLine(EVENT_BADGE_HEADER.getMessage())
                .append(summary.getEventBadgeDetails());
    }

    private DetailsFormatter append(String text) {
        sb.append(text);
        return this;
    }

    private DetailsFormatter appendLine(String text) {
        sb.append(text).append(System.lineSeparator());
        return this;
    }

    private DetailsFormatter appendLine(String text, int numberOfLines) {
        sb.append(text);
        sb.append(String.valueOf(System.lineSeparator()).repeat(Math.max(0, numberOfLines)));
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
