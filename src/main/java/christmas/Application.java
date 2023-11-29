package christmas;

import christmas.controller.ChristmasPromotionController;
import christmas.model.policy.discount.DdayDiscount;
import christmas.model.policy.discount.SpecialDiscount;
import christmas.model.policy.discount.WeekdayDiscount;
import christmas.model.policy.discount.WeekendDiscount;
import christmas.model.policy.event.GiveawayEvent;
import christmas.service.ChristmasPromotionService;
import christmas.service.util.BenefitCalculator;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ChristmasPromotionController christmasPromotionController = new ChristmasPromotionController(
                new ChristmasPromotionService(new BenefitCalculator(
                        List.of(new DdayDiscount(), new WeekdayDiscount(), new WeekendDiscount(), new SpecialDiscount()),
                        List.of(new GiveawayEvent()))
                ));
        christmasPromotionController.start();
    }
}
