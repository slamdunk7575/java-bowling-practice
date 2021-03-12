package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @Test
    @DisplayName("SPARE 상태는 최대 점수 10점, 추가 투구 1회를 가짐")
    void spare_get_score_test() {
        // given
        State spare = Spare.of(Pin.of(5));

        // when
        Score score = spare.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(10, Remaining.of(1)));
    }

    @Test
    @DisplayName("SPARE 상태는 First 투구와 Second 투구를 더해서 점수를 계산")
    void spare_calculate_test() {
        // given
        Score baseScore = Score.of(5, Remaining.of(1));

        // when
        Score calculatedScore = Spare.of(Pin.of(2)).calculate(baseScore);

        // then
        assertThat(calculatedScore).isEqualTo(Score.of(7, Remaining.of(0)));
        assertThat(calculatedScore.isPending()).isEqualTo(Boolean.FALSE);
    }

    @Test
    @DisplayName("SPARE 상태에서는 더 이상 공을 굴릴 수 없는 테스트")
    void spare_not_bowl_test() {
        assertThatThrownBy(() -> Spare.of(Pin.of(7)).bowl(Pin.of(3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("SPARE 상태는 추가 투구없이 종료(True) 값을 가지는 테스트")
    void spare_is_end_test() {
        assertThat(Spare.of(Pin.of(7)).isEnd()).isTrue();
    }

    @Test
    @DisplayName("CONTINUE 상태에서 나머지 핀을 모두 쓰러뜨린 경우 SPARE 상태를 가지는 테스트")
    void continue_to_spare_test() {
        assertThat(Continue.of(Pin.of(7)).bowl(Pin.of(3)))
                .isEqualTo(Spare.of(Pin.of(7)));
    }
}