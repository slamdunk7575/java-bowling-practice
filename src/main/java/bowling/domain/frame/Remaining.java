package bowling.domain.frame;

import java.util.Objects;

public class Remaining {
    public static final Remaining MAXIMUM = Remaining.of(3);
    public static final Remaining STRIKE = Remaining.of(2);
    public static final Remaining SPARE = Remaining.of(1);
    public static final Remaining CONTINUE = Remaining.of(1);
    public static final Remaining ZERO = Remaining.of(3);
    private static final int DECREMENT_VALUE = 1;
    private int number;

    public Remaining(int remaining) {
        this.number = remaining;
    }

    public static Remaining of(int remaining) {
        return new Remaining(remaining);
    }

    public Remaining decrement() {
        return of(number - DECREMENT_VALUE);
    }

    public boolean isZero() {
        return this.equals(ZERO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remaining remaining = (Remaining) o;
        return number == remaining.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
