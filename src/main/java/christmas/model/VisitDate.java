package christmas.model;

import christmas.exception.business.InvalidDateException;
import christmas.model.constant.Calender;
import christmas.view.input.DateRequest;

import java.time.LocalDate;

import static christmas.exception.ValidationErrorMessage.INVALID_DATE;
import static christmas.model.constant.Calender.EVENT_DATE;

public record VisitDate(LocalDate date) {

    public static VisitDate of(DateRequest dateRequest) {
        LocalDate date = processVisitDate(dateRequest);
        return new VisitDate(date);
    }

    private static LocalDate processVisitDate(DateRequest dateRequest) {
        validateDateRange(dateRequest);
        return LocalDate.of(EVENT_DATE.getYear(), EVENT_DATE.getMonth(), dateRequest.getVisitDate());
    }

    private static void validateDateRange(DateRequest dateRequest) {
        if (!Calender.isInMonthRange(dateRequest.getVisitDate())) {
            throw new InvalidDateException(INVALID_DATE.getMessage());
        }
    }
}
