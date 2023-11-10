package christmas.service;

import christmas.model.VisitDate;

public class ChristmasPromotionService {
    private VisitDate visitDate;

    public void createVisitDate(Integer readVisitDate) {
        visitDate = new VisitDate(readVisitDate);
    }
}
