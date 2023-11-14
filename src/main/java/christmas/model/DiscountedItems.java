package christmas.model;

import christmas.model.vo.DiscountAmount;

import java.util.List;

public record DiscountedItems(List<DiscountAmount> discounts) {
}
