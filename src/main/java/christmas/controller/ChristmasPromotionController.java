package christmas.controller;

import christmas.service.ChristmasPromotionService;
import christmas.view.output.DetailsFormatter;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class ChristmasPromotionController {

    private final ChristmasPromotionService service;

    public ChristmasPromotionController() {
        service = new ChristmasPromotionService();
    }

    public void setVisitDate(Integer readVisitDate) {
        service.createVisitDate(readVisitDate);
    }

    public void setOrder(List<SimpleEntry<String, Integer>> readOrder) {
        service.createOrder(readOrder);
    }

    public DetailsFormatter formatOrder() {
        return new DetailsFormatter(service.getOrder());
    }

    public DetailsFormatter formatDiscountResult() {
        return new DetailsFormatter(service.getDiscountResult());
    }

    public DetailsFormatter formatVisitDate() {
        return new DetailsFormatter(service.getVisitDate());
    }
}
