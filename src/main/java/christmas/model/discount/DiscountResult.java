package christmas.model.discount;

import christmas.model.menu.Menu;

import java.util.List;
import java.util.Optional;

public class DiscountResult {
    private final Optional<Menu> giveawayItem;
    private final List<DiscountAmount> discounts;
    private final int totalDiscount;

    public DiscountResult(List<DiscountAmount> discounts, Optional<Menu> giveawayItem) {
        this.giveawayItem = giveawayItem;
        this.discounts = discounts;
        this.totalDiscount = calculateTotalDiscount(discounts);
    }

    public List<DiscountAmount> getDiscounts() {
        return discounts;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public Optional<Menu> getGiveawayItem() {
        return giveawayItem;
    }

    private int calculateTotalDiscount(List<DiscountAmount> discounts) {
        return discounts.stream().mapToInt(DiscountAmount::amount).sum();
    }
}
