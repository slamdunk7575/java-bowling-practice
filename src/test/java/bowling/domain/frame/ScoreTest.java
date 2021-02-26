package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("READY 상태 : 점수 0점, 추가 투구 3회")
    void ready_score_test() {
        assertThat(Score.ofReady()).isEqualTo(Score.of(0, Remaining.MAXIMUM));
    }

    @Test
    @DisplayName("STRIKE 상태 : 점수 10점, 추가 투구 2회")
    void strike_score_test() {
        assertThat(Score.ofStrike()).isEqualTo(Score.of(10, Remaining.STRIKE));
    }

    @Test
    @DisplayName("SPARE 상태 : 점수 10점, 추가 투구 1회")
    void spare_score_test() {
        assertThat(Score.ofSpare()).isEqualTo(Score.of(10, Remaining.SPARE));
    }

    @Test
    @DisplayName("GUTTER 상태 : 점수 0점, 추가 투구 0회")
    void gutter_score_test() {
        assertThat(Score.ofGutter()).isEqualTo(Score.of(0, Remaining.ZERO));
    }

    @Test
    @DisplayName("PENDING 상태 : 점수 0점, 추가 투구 3회")
    void pending_score_test() {
        assertThat(Score.ofPending()).isEqualTo(Score.of(0, Remaining.MAXIMUM));
    }

    @Test
    @DisplayName("pending 상태 테스트")
    void is_pending_test() {
        assertThat(Score.of(10, Remaining.of(1)).isPending()).isTrue();
        assertThat(Score.of(10, Remaining.of(0)).isPending()).isFalse();
    }

    @Test
    @DisplayName("Score 더하기 테스트")
    void score_add_test() {
        assertThat(Score.of(10, Remaining.of(1)).add(Score.of(5))).isEqualTo(Score.of(15));
    }

    @Test
    @DisplayName("Score 덧셈시 추가 횟수가 없는 경우 기존 값을 리턴")
    void score_add_remaining_zero_test() {
        assertThat(Score.of(10, Remaining.of(0)).add(Score.of(5))).isEqualTo(Score.of(10));
    }

}