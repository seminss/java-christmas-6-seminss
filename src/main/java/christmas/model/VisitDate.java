package christmas.model;

import christmas.exception.business.InvalidDateException;
import christmas.model.policy.PromotionPeriod;
import christmas.dto.request.DateRequest;

import java.time.LocalDate;

import static christmas.exception.ValidationErrorMessage.INVALID_DATE;

public record VisitDate(LocalDate date) {

    public static VisitDate of(DateRequest dateRequest) {
        LocalDate date = processVisitDate(dateRequest);
        return new VisitDate(date);
    }

    private static LocalDate processVisitDate(DateRequest dateRequest) {
        validateDateRange(dateRequest);
        return LocalDate.of(PromotionPeriod.getYear(), PromotionPeriod.getMonth(), dateRequest.getVisitDate());
    }

    private static void validateDateRange(DateRequest dateRequest) {
        if (!PromotionPeriod.isInMonthRange(dateRequest.getVisitDate())) {
            throw new InvalidDateException(INVALID_DATE.getMessage());
        }
    }
}
