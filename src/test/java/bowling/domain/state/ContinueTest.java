package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContinueTest {

    @Test
    @DisplayName("CONTINUE 상태에서 이전 핀을 포함하여 1~9개의 핀을 쓰러뜨린 경우 MISS 상태가 됨")
    void continue_to_miss_test() {
        assertThat(Continue.of(Pin.of(3))
                .bowl(Pin.of(2)))
                .isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("CONTINUE 상태에서 모든 핀을 쓰러뜨리면 SPARE 상태가 됨")
    void continue_to_spare_test() {
        assertThat(Continue.of(Pin.of(7))
                .bowl(Pin.of(3)))
                .isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("CONTINUE 상태에서 이전 핀을 포함하여 아무 핀도 쓰러뜨리지 못한 경우 GUTTER 상태가 됨")
    void continue_to_gutter_test() {
        assertThat(Continue.of(Pin.of(0))
                .bowl(Pin.of(0)))
                .isInstanceOf(Gutter.class);
    }


    @Test
    @DisplayName("CONTINUE 상태이면 추가 투구 가능한 상태")
    void continue_is_end_test() {
        assertThat(Continue.of(Pin.of(5)).isEnd()).isFalse();
    }


    @Test
    @DisplayName("CONTINUE 상태이면 첫번째 투구 점수, 추가 투구 1회 Score 를 가짐")
    void continue_get_score_test() {
        // given
        State continueState = Continue.of(Pin.of(7));

        // when
        Score score = continueState.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(7, Remaining.CONTINUE));
    }

    @Test
    @DisplayName("CONTINUE 상태이면 기존 점수에 더해지고 Remaining 감소됨")
    void continue_calculate_test() {
        // given
        Score baseScore = Score.of(7, Remaining.of(1));

        // when
        Score calculateScore = Continue.of(Pin.of(7)).calculate(baseScore);

        // then
        assertThat(calculateScore).isEqualTo(Score.of(14, Remaining.of(0)));
        assertThat(calculateScore.isPending()).isEqualTo(Boolean.FALSE);
    }

}
