package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("일반 프레임에서 0~9개 핀을 쓰러트리면 상태가 계속(false)")
    void continue_test() {
        // given
        Frame normalFrame = Frame.init();

        // when
        normalFrame.bowl(Pin.of(9));

        // then
        assertThat(normalFrame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 이면 상태가 종료(True)")
    void miss_test() {
        // given
        Frame normalFrame = Frame.init();

        // when
        normalFrame.bowl(Pin.of(8));
        normalFrame.bowl(Pin.of(1));

        // then
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 STRIKE 이면 상태가 종료(True)")
    void strike_test() {
        // given
        Frame normalFrame = Frame.init();

        // when
        normalFrame.bowl(Pin.of(10));

        // then
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 CONTINUE 상태이면 쓰러뜨린 핀의 점수 + 나머지 투구 1회 Score 리턴")
    void continue_calculate_score() {
        // given
        Frame frame = Frame.init();

        // when
        frame.bowl(Pin.of(8));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(8, Remaining.CONTINUE));
    }

    @Test
    @DisplayName("일반 프레임에서 GUTTER 상태이면 바로 점수를 계산")
    void gutter_calculate_score() {
        // given
        Frame frame = Frame.init();

        // when
        frame.bowl(Pin.of(0));
        frame.bowl(Pin.of(0));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(0, Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 상태이면 바로 점수를 계산")
    void miss_calculate_score() {
        // given
        Frame frame = Frame.init();

        // when
        frame.bowl(Pin.of(3));
        frame.bowl(Pin.of(2));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(5, Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 SPARE 상태이면 쓰러뜨린 핀의 점수 + 나머지 투구 0회 Score 리턴")
    void spare_calculate_score() {
        // given
        Frame frame = Frame.init();
        frame.bowl(Pin.of(8));

        // when
        Frame current = frame.bowl(Pin.of(2));
        current.bowl(Pin.of(5));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(15, Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 STRIKE 상태이면 추가 투구 2회로 점수 계산")
    void strike_calculate_score() {
        // given
        Frame frame = Frame.init();
        Frame current = frame.bowl(Pin.of(10));

        // when
        current.bowl(Pin.of(5));
        current.bowl(Pin.of(1));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(16, Remaining.of(0)));
    }

    @Test
    @DisplayName("STRIKE 상태이면 추가 2번 보너스 점수가 더해져서 계산됨")
    void strike_calculate_score_test() {
        // given
        Frame frame = Frame.init();
        frame.bowl(Pin.of(3));
        frame.bowl(Pin.of(2));

        // when
        Score score = frame.calculateScore(Strike.of().getScore());

        // then
        assertThat(score).isEqualTo(Score.of(15, Remaining.of(0)));
    }

    @Test
    @DisplayName("SPARE 상태이면 추가 1번 보너스 점수가 더해져서 계산")
    void spare_calculate_score_test() {
        // given
        Frame frame = Frame.init();
        frame.bowl(Pin.of(8));

        // when
        Score score = frame.calculateScore(Spare.of(Pin.of(6)).getScore());

        // then
        assertThat(score).isEqualTo(Score.of(18, Remaining.of(0)));
    }

}
