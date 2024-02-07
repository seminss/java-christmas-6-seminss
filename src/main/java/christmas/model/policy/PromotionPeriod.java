package christmas.model.policy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class PromotionPeriod {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int DDAY = 25;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

    private PromotionPeriod() {

    }

    public static int getYear() {
        return YEAR;
    }

    public static int getMonth() {
        return MONTH;
    }

    public static int getDday() {
        return DDAY;
    }

    public static int getStartDay() {
        return START_DAY;
    }

    public static List<Integer> getSpecialDays() {
        return SPECIAL_DAYS;
    }

    public static boolean isInMonthRange(final int day) {
        return day >= START_DAY && day <= END_DAY;
    }

    public static boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.FRIDAY;
    }
}
