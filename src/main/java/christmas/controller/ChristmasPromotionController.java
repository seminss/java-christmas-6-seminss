package christmas.controller;

import christmas.service.ChristmasPromotionService;

public class ChristmasPromotionController {

    private final ChristmasPromotionService service;

    public ChristmasPromotionController() {
        service = new ChristmasPromotionService();
    }

    public void setVisitDate(Integer readVisitDate) {
        service.createVisitDate(readVisitDate);
    }
}
