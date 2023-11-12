package christmas.model.valueObject;

import christmas.exception.business.InvalidDateException;

import java.time.LocalDate;
import java.time.Month;

import static christmas.exception.ValidationErrorMessage.INVALID_DATE;

public final class VisitDate {
    private final LocalDate date;

    public VisitDate(int date) {
        validateRange(date);
        this.date = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, date);
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw new InvalidDateException(INVALID_DATE.getMessage());
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
