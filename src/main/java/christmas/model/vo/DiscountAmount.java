package christmas.model.vo;

import christmas.model.policy.discount.DiscountSettings;

public record DiscountAmount(DiscountSettings discountSettings, int amount) {
}
