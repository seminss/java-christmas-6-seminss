package christmas.model.policy.calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;

class ChristmasSpecialDayEvaluatorTest {

    private final ChristmasSpecialDayEvaluator evaluator = new ChristmasSpecialDayEvaluator();

    private static Stream<LocalDate> provideSpecialDays() {
        return Stream.of(
                LocalDate.of(2023, Month.DECEMBER, 3),
                LocalDate.of(2023, Month.DECEMBER, 10),
                LocalDate.of(2023, Month.DECEMBER, 17),
                LocalDate.of(2023, Month.DECEMBER, 24),
                LocalDate.of(2023, Month.DECEMBER, 25),
                LocalDate.of(2023, Month.DECEMBER, 31)
        );
    }

    private static Stream<LocalDate> provideNotSpecialDays() {
        return Stream.of(
                LocalDate.of(2023, Month.DECEMBER, 1),
                LocalDate.of(2023, Month.DECEMBER, 5),
                LocalDate.of(2023, Month.DECEMBER, 7),
                LocalDate.of(2023, Month.DECEMBER, 30)
        );
    }

    @DisplayName("3,10,17,24,25,31은 스페셜 데이다.")
    @MethodSource("provideSpecialDays")
    @ParameterizedTest
    void containSuccessTest(LocalDate date) {
        Assertions.assertTrue(evaluator.contains(date));
    }

    @DisplayName("3,10,17,24,25,31외는 스페셜 데이가 아니다.")
    @MethodSource("provideNotSpecialDays")
    @ParameterizedTest
    void containFailTest(LocalDate date) {
        Assertions.assertFalse(evaluator.contains(date));
    }

}