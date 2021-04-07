package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GutterTest {

    @DisplayName("GUTTER 상태에서 공을 굴릴 경우 예외발생")
    @Test
    void gutter_bowl_exception_test() {
        assertThatThrownBy(() -> Gutter.of().bowl(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("현재 상태는 더이상 볼링공을 굴릴 수 없습니다.");
    }

    @DisplayName("GUTTER 상태는 추가 투구없이 종료 값을 가지는 테스트")
    @Test
    void gutter_is_end_test() {
        assertThat(Gutter.of().isEnd()).isTrue();
    }

    @DisplayName("GUTTER 상태는 점수 0점, 추가 투구 0회를 가짐")
    @Test
    void gutter_get_score_test() {
        // given
        State gutter = Gutter.of();

        // when
        Score score = gutter.getScore();

        // then
        assertThat(score).isEqualTo(Score.of(0, Remaining.of(0)));
    }

    @DisplayName("GUTTER 상태는 기존 점수에 0점이 더해져 계산됨")
    @Test
    void gutter_calculate_test() {
        // given
        Score baseScore = Score.of(10, Remaining.of(2));

        // when
        Score calculatedScore = Gutter.of().calculate(baseScore);

        // then
        assertThat(calculatedScore).isEqualTo(Score.of(10, Remaining.of(0)));
        assertThat(calculatedScore.isPending()).isEqualTo(Boolean.FALSE);
    }

}
