package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class PinTest {

    @Test
    @DisplayName("핀 생성 테스트")
    public void pin_test() {
        assertThat(Pin.of(1)).isEqualTo(Pin.of(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("핀 번위가 0~10 사이가 아닐 경우 예외발생 테스트")
    void pin_exception_test(int felledPin) {
        assertThatThrownBy(() -> Pin.of(felledPin))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쓰러트린 핀의 갯수는 0~10 사이여야 합니다.");
    }

    @Test
    @DisplayName("핀 점수 더하기 테스트")
    void pin_add_test() {
        assertThat(Pin.of(1).add(Pin.of(3))).isEqualTo(Pin.of(4));
    }

    @Test
    @DisplayName("핀 점수 더하기 예외 테스트")
    void pin_add_exception_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Pin.of(9).add(Pin.of(3));
                }).withMessageMatching("쓰러트린 핀의 갯수는 0~10 사이여야 합니다.");
    }

}