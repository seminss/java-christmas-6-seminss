package christmas.model.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

public class ChristmasEventCalendar extends EventCalendar {

    private final LocalDate christmasDay = LocalDate.of(2023, Month.DECEMBER, 25);

    private final Set<LocalDate> specialDiscountDays;

    public ChristmasEventCalendar(Set<LocalDate> specialDiscountDays) { //TODO: 원시값 포장
        this.specialDiscountDays = specialDiscountDays;
    }

    @Override
    public boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    @Override
    public boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    @Override
    public boolean isSpecialDiscountDay(LocalDate date) {
        return specialDiscountDays.contains(date);
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