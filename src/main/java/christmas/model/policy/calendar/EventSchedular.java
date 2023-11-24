package christmas.model.policy.calendar;

import java.time.LocalDate;

public abstract class EventSchedular {
    public abstract boolean isWeekday(LocalDate date);

    public abstract boolean isWeekend(LocalDate date);

    public abstract boolean isSpecialDiscountDay(LocalDate date);

    public abstract boolean isDDayDiscountDay(LocalDate date);
}