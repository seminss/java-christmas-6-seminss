package christmas.model.calendar;

import java.time.LocalDate;

public abstract class EventCalendar {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public abstract boolean isWeekday(LocalDate date);
    public abstract boolean isWeekend(LocalDate date);
    public abstract boolean isSpecialDiscountDay(LocalDate date);
    public abstract boolean isDDayDiscountDay(LocalDate date);
    public abstract int remainUntilDDay(LocalDate date);
}