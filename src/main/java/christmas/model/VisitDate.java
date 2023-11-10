package christmas.model;

import christmas.exception.business.InvalidDateException;

import static christmas.exception.ValidationErrorMessage.INVALID_DATE;

public record VisitDate(int date) {
    public VisitDate {
        validateRange(date);
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw new InvalidDateException(INVALID_DATE.getMessage());
        }
    }
}
