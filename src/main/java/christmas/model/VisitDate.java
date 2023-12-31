package christmas.model;

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
        if (date < 1 || date > 31) { // TODO: DECEMBER 성질은 따로 묶을 수 있게 하기
            throw new InvalidDateException(INVALID_DATE.getMessage());
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
