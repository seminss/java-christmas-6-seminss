package christmas.model.policy.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.Set;

public class SpecialDays {
    private final Set<LocalDate> dates;

    public SpecialDays() {
        this.dates = initializeSpecialDays();
    }

    public boolean contains(LocalDate date) {
        return dates.contains(date);
    }

    public static Set<LocalDate> initializeSpecialDays() {
        Set<LocalDate> specialDays = new HashSet<>();

        // 12월의 모든 일요일 추가
        LocalDate date = LocalDate.of(2023, 12, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY));
        while (date.getMonthValue() == 12) {
            specialDays.add(date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        }

        // 크리스마스 추가
        specialDays.add(LocalDate.of(2023, 12, 25));

        return specialDays;
    }
}
