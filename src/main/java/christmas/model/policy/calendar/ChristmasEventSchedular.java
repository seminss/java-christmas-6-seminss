package christmas.model.policy.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.now;

public class ChristmasEventSchedular extends EventCalendar {

    private final LocalDate christmasDate = LocalDate.of(now().getYear(), Month.DECEMBER, 25);
    private final ChristmasSpecialDayEvaluator specialDates = new ChristmasSpecialDayEvaluator();

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
        return specialDates.contains(date);
    }

    @Override
    public boolean isDDayDiscountDay(LocalDate date) {
        return !date.isAfter(christmasDate);
    }
}