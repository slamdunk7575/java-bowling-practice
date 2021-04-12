package bowling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("참가자 이름이 3글자 초과시 예외 발생")
    void user_throw_exception_test() {
        assertThatThrownBy(() -> User.of("kjyang"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("사용자 이름 길이는 1~3글자여야 합니다.");
    }

}
