package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndFrameTest {

    @Test
    @DisplayName("마지막 프레임에서 STRIKE 이면 1회 보너스 투구 기회를 가짐")
    void strike_test() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 SPARE 이면 1회 보너스 투구 기회를 가짐")
    void spare_test() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(9));
        frame.bowl(Pin.of(1));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 MISS 이면 보너스 투구는 주어지지 않음")
    void miss_test() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(8));
        frame.bowl(Pin.of(1));

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("마지막 프레임에서 모두 STRIKE 이면 점수 계산")
    void strike_get_score_test() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(30));
    }

    @Test
    @DisplayName("마지막 프레임에서 SPARE 이면 점수 계산")
    void spare_get_score_test() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(9));
        frame.bowl(Pin.of(1));
        frame.bowl(Pin.of(10));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(20));
    }

    @Test
    @DisplayName("마지막 프레임에서 MISS 이면 점수 계산")
    void miss_get_score_test() {
        // given
        Frame frame = EndFrame.of();

        // when
        frame.bowl(Pin.of(6));
        frame.bowl(Pin.of(1));

        // then
        assertThat(frame.getScore()).isEqualTo(Score.of(7));
    }

}
