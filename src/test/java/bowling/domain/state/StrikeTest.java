package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StrikeTest {

    @Test
    @DisplayName("STRIKE 상태에서 추가로 공을 굴릴 경우 예외 발생")
    void strike_bowl_exception_test() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    Strike.of().bowl(Pin.of(10));
                }).withMessageMatching("현재 상태는 더이상 볼링공을 굴릴 수 없습니다.");
    }

    @Test
    @DisplayName("STRIKE 상태는 완료(True) 상태를 가짐")
    void strike_isEnd_test() {
        assertThat(Strike.of().isEnd()).isTrue();
    }

    @Test
    @DisplayName("STRIKE 상태는 점수 10점, 추가 투구 가능 2회를 가짐")
    void strike_getScore_test() {
        // given
        State strike = Strike.of();

        // when
        Score strikeScore = strike.getScore();

        // then
        assertThat(strikeScore).isEqualTo(Score.of(10, Remaining.STRIKE));
    }

    @Test
    @DisplayName("STRIKE 상태는 기존 점수에 10점이 더해짐")
    void strike_calculate_test() {
        // given
        Score baseScore = Score.of(10, Remaining.of(2));

        // when
        Score calculatedScore = Strike.of().calculate(baseScore);

        // then
        assertThat(calculatedScore).isEqualTo(Score.of(20, Remaining.of(1)));
        assertThat(calculatedScore.isPending()).isTrue();
    }

}