package christmas.model.policy.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class ChristmasEventCalendar extends EventCalendar {

    private final LocalDate christmasDay = LocalDate.of(2023, Month.DECEMBER, 25);
    private final SpecialDays specialDays = new SpecialDays();

    @Override
    public boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.FRIDAY;
    }

    @Override
    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.FRIDAY;
    }

    @Override
    public boolean isSpecialDiscountDay(LocalDate date) {
        return specialDays.contains(date);
    }

    @Override
    public boolean isDDayDiscountDay(LocalDate date) {
        return date.getDayOfMonth() <= christmasDay.getDayOfMonth();
    }

    @Override
    public int remainUntilDDay(LocalDate date) {
        return christmasDay.getDayOfMonth() - date.getDayOfMonth();
    }
}