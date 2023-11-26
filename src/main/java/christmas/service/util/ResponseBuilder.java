package christmas.service.util;

import christmas.dto.response.PromotionResponse;
import christmas.model.Order;
import christmas.model.TotalPromotionOutcome;
import christmas.model.TotalPromotionOutcome.DiscountDetail;
import christmas.model.VisitDate;
import christmas.model.constant.Badge;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.model.constant.Giveaway.CHAMPAGNE_GIVEAWAY;
import static christmas.config.EventSymbol.DATA_FORMAT;

public class ResponseBuilder {

    private static final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());
    private static final String DEFAULT_MESSAGE = "없음";
    private static final int DEFAULT_DISCOUNT = 0;
    private static final String MENU_FORMAT = "%s %d개";
    private static final String DATE_FORMAT = "%d월 %d일";
    private static final String DISCOUNT_AMOUNT_FORMAT = "%s: %s";

    private ResponseBuilder(){

    }

    public static PromotionResponse build(Order order, VisitDate visitDate, TotalPromotionOutcome promotionOutcome) {
        if (!order.canReceivePromotion()) {
            return createBasicResponse(order, visitDate);
        }
        return createFullResponse(order, visitDate, promotionOutcome);
    }

    private static PromotionResponse createBasicResponse(Order order, VisitDate visitDate) {
        return new PromotionResponse.Builder()
                .visitDateDetails(formatVisitDate(visitDate))
                .orderDetails(formatOrder(order))
                .baseOrderDetails(formatAmount(order.getBaseOrderAmount()))
                .giveawayDetails(getDefaultMessage())
                .discountDetails(getDefaultMessage())
                .totalDiscountAmountDetails(getDefaultDiscount())
                .finalPaymentAmountDetails(formatAmount(order.getBaseOrderAmount()))
                .eventBadgeDetails(getDefaultMessage())
                .build();
    }

    public static PromotionResponse createFullResponse(Order order, VisitDate visitDate, TotalPromotionOutcome promotionOutcome) {
        return new PromotionResponse.Builder()
                .visitDateDetails(formatVisitDate(visitDate))
                .orderDetails(formatOrder(order))
                .baseOrderDetails(formatAmount(order.getBaseOrderAmount()))
                .giveawayDetails(formatGiveaway(promotionOutcome))
                .discountDetails(formatDiscount(promotionOutcome.discounts()))
                .totalDiscountAmountDetails(formatAmount(promotionOutcome.totalBenefitAmount()))
                .finalPaymentAmountDetails(formatAmount(calculateFinalPayment(order, promotionOutcome)))
                .eventBadgeDetails(formatEventBadge(Badge.of(promotionOutcome.totalBenefitAmount())))
                .build();
    }
    private static int calculateFinalPayment(Order order, TotalPromotionOutcome promotionOutcome) {
        return order.getBaseOrderAmount() - promotionOutcome.getGiveawayAmount();
    }

    private static String formatVisitDate(VisitDate visitDate) {
        return String.format(DATE_FORMAT,
                visitDate.date().getMonthValue(), visitDate.date().getDayOfMonth());
    }

    public static String formatOrder(Order order) {
        return order.getOrderedMenu().entrySet().stream()
                .map(entry -> String.format(MENU_FORMAT, entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

    public static String formatGiveaway(TotalPromotionOutcome outcome) {
        if (outcome.getGiveawayAmount() < 0) {
            return DEFAULT_MESSAGE;
        }
        return String.format(MENU_FORMAT, CHAMPAGNE_GIVEAWAY.getMenu(), CHAMPAGNE_GIVEAWAY.getQuantity());
    }

    public static String formatDiscount(List<DiscountDetail> discounts) {
        if (discounts.stream().allMatch(d -> d.amount() == DEFAULT_DISCOUNT)) {
            return DEFAULT_MESSAGE;
        }
        return discounts.stream()
                .filter(d -> d.amount() < DEFAULT_DISCOUNT)
                .map(ResponseBuilder::formatDiscountDetail)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static String formatEventBadge(Badge eventBadge) {
        if (eventBadge == Badge.NONE) {
            return DEFAULT_MESSAGE;
        }
        return eventBadge.getName();
    }

    public static String getDefaultDiscount() {
        return df.format(DEFAULT_DISCOUNT);
    }

    public static String formatAmount(int finalPaymentAmount) {
        return df.format(finalPaymentAmount);
    }

    private static String formatDiscountDetail(DiscountDetail discount) {
        return String.format(DISCOUNT_AMOUNT_FORMAT, discount.name(), df.format(discount.amount()));
    }
}
