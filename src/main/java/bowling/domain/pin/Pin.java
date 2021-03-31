package bowling.domain.pin;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;

import java.util.Objects;
import java.util.stream.IntStream;

public class Pin {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Pin[] CACHE = IntStream.rangeClosed(MIN, MAX)
                                                .mapToObj(Pin::new)
                                                .toArray(Pin[]::new);
    private final int felledPinCount;

    private Pin(int felledPin) {
        this.felledPinCount = felledPin;
    }

    public static Pin of(int countOfPin) {
        try {
            return CACHE[countOfPin];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("쓰러트린 핀의 갯수는 0~10 사이여야 합니다.");
        }
    }

    public Pin add(Pin felledPin) {
        if (felledPin.felledPinCount == MIN) {
            return this;
        }
        return of(this.felledPinCount + felledPin.felledPinCount);
    }

    public boolean isAllFelled() {
        return felledPinCount == MAX;
    }

    public boolean isNotFelled() {
        return felledPinCount == MIN;
    }

    public Score toScore() {
        return Score.of(felledPinCount);
    }

    public Score toScore(Remaining remaining) {
        return Score.of(felledPinCount, remaining);
    }

    public Score toComplementedScore() {
        return Score.of(MAX - felledPinCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return felledPinCount == pin.felledPinCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(felledPinCount);
    }
}
