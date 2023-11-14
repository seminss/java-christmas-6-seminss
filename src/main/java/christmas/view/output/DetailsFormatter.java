package christmas.view.output;

import christmas.config.Menu;
import christmas.model.DiscountedItems;
import christmas.model.Order;
import christmas.model.summary.PromotionSummary;
import christmas.model.VisitDate;

import java.text.DecimalFormat;

import static christmas.constant.EventSymbol.DATA_FORMAT;

public class DetailsFormatter {
    private final StringBuilder sb = new StringBuilder();
    private final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());
    private static final String ONE_LINE = System.lineSeparator();
    private static final String TWO_LINE = ONE_LINE + ONE_LINE;

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
        sb.append(ONE_LINE).append("<주문 메뉴>").append(ONE_LINE);
        for (Menu menu : order.getOrderedMenu().keySet()) {
            sb.append(menu.getName())
                    .append(" ")
                    .append(order.getOrderedMenu().get(menu)).append("개")
                    .append(TWO_LINE);
        }
        sb.append("<할인 전 총주문 금액>").append(ONE_LINE);
        sb.append(df.format(order.calculateTotalOrderAmount())).append("원");
    }

    public DetailsFormatter(PromotionSummary summary) {
        sb.append(ONE_LINE)
                .append("<증정 메뉴>" + ONE_LINE)
                .append(summary.giveawayItemDetails() + TWO_LINE)
                .append("<혜택 내역>" + ONE_LINE)
                .append(summary.discountDetails() + TWO_LINE)
                .append("<총혜택 금액>" + ONE_LINE)
                .append(summary.totalDiscountDetails() + TWO_LINE)
                .append("<할인 후 예상 결제 금액>" + ONE_LINE)
                .append(summary.finalPaymentAmountDetails() + TWO_LINE)
                .append("<12월 이벤트 배지>" + ONE_LINE)
                .append(summary.eventBadgeDetails());
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
