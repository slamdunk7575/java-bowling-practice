package bowling.domain.frame;

import java.util.Objects;

public class Score {
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private int number;
    private Remaining remaining;

    private Score(int number, Remaining remaining) {
        this.number = number;
        this.remaining = remaining;
    }

    public static Score of(int number) {
        return new Score(number, Remaining.ZERO);
    }

    public static Score of(int number, Remaining remaining) {
        return new Score(number, remaining);
    }

    public static Score ofReady() {
        return new Score(MIN_SCORE, Remaining.MAXIMUM);
    }

    public static Score ofStrike() {
        return new Score(MAX_SCORE, Remaining.STRIKE);
    }

    public static Score ofSpare() {
        return new Score(MAX_SCORE, Remaining.SPARE);
    }

    public static Score ofGutter() {
        return new Score(MIN_SCORE, Remaining.ZERO);
    }

    public static Score ofPending() {
        return new Score(MIN_SCORE, Remaining.MAXIMUM);
    }

    public Score add(Score otherScore) {
        return remaining.isZero() ? this : of(this.number + otherScore.getNumber(), remaining.decrement());
    }

    public boolean isPending() {
        return !remaining.isZero();
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return number == score.number && Objects.equals(remaining, score.remaining);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, remaining);
    }
}
