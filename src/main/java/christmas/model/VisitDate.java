package christmas.model;

import christmas.exception.business.InvalidDateException;

import java.time.LocalDate;
import java.time.Month;

import static christmas.exception.ValidationErrorMessage.INPUT_NOT_INTEGER;

public final class VisitDate {
    private final LocalDate date;

    public VisitDate(int date) {
        validateRange(date);
        this.date = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, date);
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) { //TODO: LocalDate로 바꾸기
            throw new InvalidDateException(INPUT_NOT_INTEGER.getMessage());
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
