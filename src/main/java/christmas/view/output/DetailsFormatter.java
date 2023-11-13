package christmas.view.output;

import christmas.constant.Menu;
import christmas.model.discount.DiscountResult;
import christmas.model.order.Order;
import christmas.model.vo.VisitDate;

import java.text.DecimalFormat;

public class DetailsFormatter {
    private final StringBuilder sb;
    private final DecimalFormat df = new DecimalFormat("###,###");

    public DetailsFormatter(VisitDate date) {
        sb = new StringBuilder();
        sb.append("12월 ")
                .append(date.getDate())
                .append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public DetailsFormatter(Order order) {
        sb = new StringBuilder();
        sb.append("<주문 메뉴>\n");
        for (Menu menu : order.getOrderedMenu().keySet()) {
            sb.append(menu.getName())
                    .append(" ")
                    .append(order.getOrderedMenu().get(menu)).append("개\n");
        }
        sb.append("\n<할인 전 총주문 금액>\n");
        sb.append(df.format(order.getInitialOrderAmount())).append("원");
    }

    public DetailsFormatter(DiscountResult result) {
        sb = new StringBuilder();
        sb.append("dsffd");
    }

    public static DetailsFormatter OrderFormatter(Order order) {
        return new DetailsFormatter(order);
    }

    public static DetailsFormatter DiscountResultFormatter(DiscountResult result) {
        return new DetailsFormatter(result);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
