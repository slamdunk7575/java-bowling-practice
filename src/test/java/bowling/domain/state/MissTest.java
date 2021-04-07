package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {

    @DisplayName("MISS 상태에서 공을 굴릴 경우 예외발생")
    @Test
    void miss_bowl_exception_test() {
        assertThatThrownBy(() -> Miss.of(Pin.of(3), Pin.of(6)).bowl(Pin.of(10)))
            .isInstanceOf(IllegalArgumentException.class).hasMessageMatching("현재 상태는 더이상 볼링공을 굴릴 수 없습니다.");
    }

    @DisplayName("MISS 상태는 추가 없이 종료값을 가지는 테스트")
    @Test
    void miss_is_end_test() {
        assertThat(Miss.of(Pin.of(3), Pin.of(6)).isEnd()).isTrue();
    }

    @DisplayName("MISS 상태는 두수의 합을 점수로 가지고 추가 투구 0회를 가짐")
    @Test
    void miss_get_score_test() {
        // given
        State miss = Miss.of(Pin.of(3), Pin.of(4));

        // when
        Score score = miss.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(7, Remaining.of(0)));
    }

    @DisplayName("MISS 상태는 기존 점수에 두수의 합이 더해짐")
    @Test
    void miss_calculate_test() {
        // given
        Score baseScore = Score.of(10, Remaining.of(2));

        // when
        Score calculatedScore = Miss.of(Pin.of(3), Pin.of(2)).calculate(baseScore);

        // then
        assertThat(calculatedScore).isEqualTo(Score.of(15, Remaining.of(0)));
        assertThat(calculatedScore.isPending()).isEqualTo(Boolean.FALSE);
    }

}
