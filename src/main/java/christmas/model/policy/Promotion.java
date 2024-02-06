package christmas.model.policy;

import christmas.model.Order;
import christmas.model.VisitDate;

public interface Promotion {
    int DEFAULT_DISCOUNT_AMOUNT = 0;
    int PROMOTION_THRESHOLD = 10000;
    String getName();

    int calculateBenefit(Order order, VisitDate visitDate);

}
