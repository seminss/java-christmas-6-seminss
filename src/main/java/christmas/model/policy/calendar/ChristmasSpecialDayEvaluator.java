package christmas.model.policy.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.Set;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.LocalDate.now;
import static java.time.Month.DECEMBER;

public class ChristmasSpecialDayEvaluator {
    private static final LocalDate christmasDate = LocalDate.of(now().getYear(), Month.DECEMBER, 25);
    private static final LocalDate startDateOfDecember = LocalDate.of(now().getYear(), Month.DECEMBER, 1);
    private final Set<LocalDate> specialDays;

    public ChristmasSpecialDayEvaluator() {
        this.specialDays = initializeSpecialDays();
    }

    public boolean contains(LocalDate date) {
        return specialDays.contains(date);
    }

    private static Set<LocalDate> initializeSpecialDays() {
        Set<LocalDate> specialDays = new HashSet<>();
        //12월의 모든 일요일을 스페셜 데이에 추가
        LocalDate date = startDateOfDecember.with(TemporalAdjusters.firstInMonth(SUNDAY));
        while (date.getMonth() == DECEMBER) {
            specialDays.add(date);
            date = date.with(TemporalAdjusters.next(SUNDAY));
        }
        //크리스마스를 스페셜 데이에 추가
        specialDays.add(christmasDate);
        return specialDays;
    }
}
