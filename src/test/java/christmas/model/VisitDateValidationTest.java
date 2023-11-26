package christmas.model;

import christmas.dto.request.DateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Nested
@DisplayName("VisitDate 모델 validate 테스트")
class VisitDateValidationTest {

    private static Stream<DateRequest> provideRangeTrue() {
        return Stream.of(
                DateRequest.of("1"),
                DateRequest.of("10"),
                DateRequest.of("20"),
                DateRequest.of("31")
        );
    }

    private static Stream<DateRequest> provideRangeFalse() {
        return Stream.of(
                DateRequest.of("0"),
                DateRequest.of("32"),
                DateRequest.of("-1"),
                DateRequest.of("40")
        );
    }

    @DisplayName("방문 날짜가 1 이상 31 이하면 성공한다.")
    @ParameterizedTest
    @MethodSource("provideRangeTrue")
    void success(DateRequest dateRequest) {
        Assertions.assertDoesNotThrow(() -> VisitDate.of(dateRequest));
    }

    @DisplayName("방문 날짜가 1 미만 31 초과면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("provideRangeFalse")
    void fail(DateRequest dateRequest) {
        assertThatThrownBy(() -> VisitDate.of(dateRequest)).isInstanceOf(IllegalArgumentException.class);
    }
}
