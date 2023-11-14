package christmas.view.output;

import christmas.config.Menu;
import christmas.model.DiscountBenefits;
import christmas.model.Order;
import christmas.model.PromotionSummary;
import christmas.model.VisitDate;

import java.text.DecimalFormat;

import static christmas.constant.EventSymbol.DATA_FORMAT;

public class DetailsFormatter {
    private final StringBuilder sb = new StringBuilder();
    private final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());

    public DetailsFormatter(DiscountBenefits result) {
    }

    public static DetailsFormatter visitDateFormatter(VisitDate date) {
        return new DetailsFormatter(date);
    }

    public static DetailsFormatter OrderFormatter(Order order) {
        return new DetailsFormatter(order);
    }

    public static DetailsFormatter DiscountResultFormatter(PromotionSummary summary) {
        return new DetailsFormatter(summary);
    }

    public DetailsFormatter(VisitDate date) {
        sb.append("12월 ")
                .append(date.getDate().getDayOfMonth())
                .append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public DetailsFormatter(Order order) {
        sb.append("\n<주문 메뉴>\n");
        for (Menu menu : order.getOrderedMenu().keySet()) {
            sb.append(menu.getName())
                    .append(" ")
                    .append(order.getOrderedMenu().get(menu)).append("개\n");
        }
        sb.append("\n<할인 전 총주문 금액>\n");
        sb.append(df.format(order.getInitialOrderAmount())).append("원");
    }

    public DetailsFormatter(PromotionSummary summary) {
        sb.append("\n<증정 메뉴>\n")
                .append(summary.formattedGiveawayItem())
                .append("\n\n<혜택 내역>\n")
                .append(summary.formattedDiscountDetails())
                .append("\n\n<총혜택 금액>\n")
                .append(summary.formattedTotalDiscount())
                .append("\n\n<할인 후 예상 결제 금액>\n")
                .append(summary.formattedFinalPaymentAmount())
                .append("\n\n<12월 이벤트 배지>\n")
                .append(summary.formattedEventBadge());
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
