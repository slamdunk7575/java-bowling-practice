package bowling.domain.game;

import bowling.domain.pin.Pin;
import bowling.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @Test
    @DisplayName("STRIKE 경우 다음 사람에게 차례가 넘어감")
    void strike_change_user_test() {
        // given
        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList("YKJ", "KKK"));

        // when
        bowlingGame.bowl(Pin.of(10));

        // then
        assertThat(bowlingGame.getCurrentUser()).isEqualTo(User.of("KKK"));
    }

    @Test
    @DisplayName("SPARE 경우 다음 사람에게 차례가 넘어가는 테스트")
    void spare_change_user_test() {
        // given
        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList("YKJ", "KKK"));

        // when
        bowlingGame.bowl(Pin.of(3));
        bowlingGame.bowl(Pin.of(7));

        // then
        assertThat(bowlingGame.getCurrentUser()).isEqualTo(User.of("KKK"));
    }

    @Test
    @DisplayName("CONTINUE 경우 계속 차례가 유지되는 테스트")
    void continue_not_change_user_test() {
        // given
        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList("YKJ", "KKK"));

        // when
        bowlingGame.bowl(Pin.of(3));

        // then
        assertThat(bowlingGame.getCurrentUser()).isEqualTo(User.of("YKJ"));
    }

    @Test
    @DisplayName("현재 순서의 참가자를 리턴")
    void current_user_test() {
        // given
        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList("YKJ", "KKK"));

        // when & then
        assertThat(bowlingGame.getCurrentUser()).isEqualTo(User.of("YKJ"));
    }

    @Test
    @DisplayName("모든 투구가 끝나야 경기가 종료")
    void game_over_test() {
        // given
        BowlingGame bowlingGame = BowlingGame.of(Arrays.asList("YKJ", "KKK"));

        // when
        IntStream.rangeClosed(1, 24).forEach(index -> bowlingGame.bowl(Pin.of(10)));

        // then
        assertThat(bowlingGame.isGameOver()).isTrue();
    }
}
