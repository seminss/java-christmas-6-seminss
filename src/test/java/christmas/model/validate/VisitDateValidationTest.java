package christmas.model.validate;

import christmas.model.VisitDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Nested
@DisplayName("VisitDate 모델 validate 테스트")
public class VisitDateValidationTest {
    @DisplayName("방문 날짜가 1 이상 31 이하면 성공한다.")
    @ValueSource(ints = {1, 10, 20, 31})
    @ParameterizedTest
    public void success(int number) {
        Assertions.assertEquals(new VisitDate(number).getDate(), LocalDate.of(2023, Month.DECEMBER, number));
    }

    @DisplayName("방문 날짜가 1 미만 31 초과면 예외가 발생한다.")
    @ValueSource(ints = {0, 32, -1, 40})
    @ParameterizedTest
    public void fail(int number) {
        assertThatThrownBy(() -> new VisitDate(number));
    }
}
