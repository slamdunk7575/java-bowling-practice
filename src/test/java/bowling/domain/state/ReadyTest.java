package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("READY 에서 10개 미만의 핀을 쓰러뜨리면 CONTINUE 상태가 되는 테스트")
    void ready_to_continue_test() {
        assertThat(Ready.of().bowl(Pin.of(0))).isInstanceOf(Continue.class);
    }

    @Test
    @DisplayName("READY 에서 모든 핀을 쓰러뜨리면 STRIKE 상태가 되는 테스트")
    void ready_to_strike_test() {
        assertThat(Ready.of().bowl(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("READY 상태이면 추가 투구 가능값을 가지는 테스트")
    void ready_isEnd_test() {
        assertThat(Ready.of().isEnd()).isFalse();
    }

    @Test
    @DisplayName("READY 상태이면 점수 0점, 추가 투구 가능 3회를 가짐")
    void ready_getScore_test() {
        // given
        State ready = Ready.of();

        // when
        Score score = ready.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(0, Remaining.of(3)));
    }

    @Test
    @DisplayName("READY 상태 계산은 기존 점수를 리턴")
    void ready_calculate_test() {
        // given
        Score baseScore = Score.of(7, Remaining.of(1));

        // when
        Score calculatedScore = Ready.of().calculate(baseScore);

        // then
        assertThat(calculatedScore).isEqualTo(Score.of(7, Remaining.of(1)));
        assertThat(calculatedScore.isPending()).isTrue();
    }

}
