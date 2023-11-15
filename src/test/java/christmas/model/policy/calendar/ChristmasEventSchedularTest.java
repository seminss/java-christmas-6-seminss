package christmas.model.policy.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

class ChristmasEventSchedularTest {

    private final EventSchedular eventSchedular = new ChristmasEventSchedular();

    private static Stream<LocalDate> provideWeekdays() {
        return Stream.of(
                LocalDate.of(2023, Month.DECEMBER, 4), // 월요일
                LocalDate.of(2023, Month.DECEMBER, 5), // 화요일
                LocalDate.of(2023, Month.DECEMBER, 6), // 수요일
                LocalDate.of(2023, Month.DECEMBER, 7)  // 목요일
        );
    }

    private static Stream<LocalDate> provideWeekends() {
        return Stream.of(
                LocalDate.of(2023, Month.DECEMBER, 8), // 금요일
                LocalDate.of(2023, Month.DECEMBER, 9) // 토요일
        );
    }

    @DisplayName("일,월,화,수,목 이면 평일이다.")
    @MethodSource("provideWeekdays")
    @ParameterizedTest
    void isWeekdaySuccessTest(LocalDate date) {
        Assertions.assertTrue(eventSchedular.isWeekday(date));
    }

    @DisplayName("금,토은 평일이 아니다.")
    @MethodSource("provideWeekends")
    @ParameterizedTest
    void isWeekdayFailTest(LocalDate date) {
        Assertions.assertFalse(eventSchedular.isWeekday(date));
    }

    @DisplayName("금,토는 주말이다.")
    @MethodSource("provideWeekends")
    @ParameterizedTest
    void isWeekendSuccessTest(LocalDate date) {
        Assertions.assertTrue(eventSchedular.isWeekend(date));
    }

    @DisplayName("일,월,화,수,목은 주말이 아니다.")
    @MethodSource("provideWeekdays")
    @ParameterizedTest
    void isWeekendFailTest(LocalDate date) {
        Assertions.assertFalse(eventSchedular.isWeekend(date));
    }

    @DisplayName("1일부터 25일까지 D-Day 할인을 받을 수 있다.")
    @Test
    void isDDayDiscountDaySuccessTest() {
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 25);
        Assertions.assertTrue(eventSchedular.isDDayDiscountDay(date));
    }

    @DisplayName("26일 부터는 D-Day 할인을 받을 수 없다.")
    @Test
    void isDDayDiscountDayFailTest() {
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 26);
        Assertions.assertFalse(eventSchedular.isDDayDiscountDay(date));
    }

    @DisplayName("ChristmasSpecialDayEvaluator에서 판단한 결과가 그대로 적용 된다.")
    @Test
    void containSuccessTest() {
        LocalDate date = LocalDate.of(2023, Month.DECEMBER, 17);
        Assertions.assertTrue(eventSchedular.isSpecialDiscountDay(date));
    }
}