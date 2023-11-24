package christmas.model.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum Calender {
    EVENT_DATE(2023, 12, 25),
    START_DATE(2023, 12,1),
    END_DATE(2023, 12,31);

    private final int year;
    private final int month;
    private final int day;


    Calender(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static boolean isInMonthRange(final int day){
        return day>= START_DATE.day && day<= END_DATE.day;
    }

    public static boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.FRIDAY;
    }

}
