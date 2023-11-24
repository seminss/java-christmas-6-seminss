package christmas.input;

import christmas.view.input.DateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Nested
@DisplayName("방문 날짜 입력 검증 테스트")
class DateRequestTest {

    @DisplayName("정수면 성공이다.")
    @ValueSource(strings = {"1", "-1", "0"})
    @ParameterizedTest
    void dateRequestSuccess1(String userInput) {
        assertThat(DateRequest.valueOf(userInput)).isNotNull();

    }

    @DisplayName("실수면 예외가 발생한다.")
    @ValueSource(strings = {"1.5", "-2.4"})
    @ParameterizedTest
    void dateRequestFail1(String userInput) {
        assertThrows(IllegalArgumentException.class, () -> DateRequest.valueOf(userInput));
    }

    @DisplayName("값이 없으면 예외가 발생한다.")
    @ValueSource(strings = {""})
    @ParameterizedTest
    void dateRequestFail2(String userInput) {
        assertThrows(IllegalArgumentException.class, () -> DateRequest.valueOf(userInput));
    }


    @DisplayName("문자가 들어오면 예외가 발생한다.")
    @ValueSource(strings = {"&", "5&", "-", "5,5", "1-4"})
    @ParameterizedTest
    void dateRequestFail3(String userInput) {
        assertThrows(IllegalArgumentException.class, () -> DateRequest.valueOf(userInput));
    }

    @DisplayName("공백이 있으면 예외가 발생한다.")
    @ValueSource(strings = {"   5", "5   ", "1    2"})
    @ParameterizedTest
    void dateRequestFail4(String userInput) {
        assertThrows(IllegalArgumentException.class, () -> DateRequest.valueOf(userInput));
    }
}
