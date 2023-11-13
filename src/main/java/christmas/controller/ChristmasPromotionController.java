package christmas.controller;

import christmas.service.ChristmasPromotionService;

import java.util.AbstractMap;
import java.util.List;

public class ChristmasPromotionController {

    private final ChristmasPromotionService service;

    public ChristmasPromotionController() {
        service = new ChristmasPromotionService();
    }

    public void setVisitDate(Integer readVisitDate) {
        service.createVisitDate(readVisitDate);
    }

    public void setOrder(List<AbstractMap.SimpleEntry<String, Integer>> readOrder) {
        service.createOrder(readOrder);
    }

    public void makeResult() {
        service.generateDiscountResult();
    }
}
