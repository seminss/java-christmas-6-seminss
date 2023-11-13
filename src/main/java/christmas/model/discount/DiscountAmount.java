package christmas.model.discount;

import christmas.model.discount.policy.DiscountConfig;

public record DiscountAmount(DiscountConfig discountConfig, int amount) {
}
