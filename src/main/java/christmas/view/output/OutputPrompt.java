package christmas.view.output;

import christmas.dto.response.PromotionResponse;

import static christmas.view.output.PromptMessage.*;

public class OutputPrompt {
    private final StringBuilder sb = new StringBuilder();

    public OutputPrompt(PromotionResponse summary) {
        appendLine(VISITDATE_MESSEAGE.formatMessage(summary.getVisitDateDetails()), 2)
                .appendLine(ORDER_HEADER.getMessage())
                .appendLine(summary.getOrderDetails(), 2)
                .appendLine(BASE_ORDER_AMOUNT_HEADER.getMessage())
                .appendLine(summary.getBaseOrderAmount(), 2)
                .appendLine(GIVEAWAY_HEADER.getMessage())
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

    public static OutputPrompt of(PromotionResponse summary) {
        return new OutputPrompt(summary);
    }

    private OutputPrompt append(String text) {
        sb.append(text);
        return this;
    }

    private OutputPrompt appendLine(String text) {
        sb.append(text).append(System.lineSeparator());
        return this;
    }

    private OutputPrompt appendLine(String text, int numberOfLines) {
        sb.append(text);
        sb.append(String.valueOf(System.lineSeparator()).repeat(Math.max(0, numberOfLines)));
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
