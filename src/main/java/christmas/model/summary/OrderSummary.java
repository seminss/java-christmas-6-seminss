package christmas.model.summary;

import christmas.model.Order;

import java.text.DecimalFormat;
import java.util.stream.Collectors;

import static christmas.constant.EventSymbol.DATA_FORMAT;

public class OrderSummary {

    private static final DecimalFormat df = new DecimalFormat(DATA_FORMAT.getValue());
    private static final String ORDER_FORMAT = "%s %d개";

    private String orderDetails;
    private String baseOrderAmount;

    public OrderSummary(Order order) {
        initOrderDetails(order);
        initBaseOrderAmountDetails(order);
    }

    public String getBaseOrderAmountDetails() {
        return this.orderDetails;
    }

    public String getOrderDetails() {
        return this.baseOrderAmount;
    }

    private void initOrderDetails(Order order) {
        this.orderDetails = order.getOrderedMenu().entrySet().stream()
                .map(entry -> String.format(ORDER_FORMAT, entry.getKey().getName(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private void initBaseOrderAmountDetails(Order order) {
        this.baseOrderAmount = df.format(order.calculateBaseOrderAmount());
    }
}
