package bowling.domain.state;

import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;

import java.util.Objects;

public class Spare extends FinishedState {

    private final Pin first;

    private Spare(Pin first) {
        this.first = first;
    }

    public static Spare of(Pin first) {
        return new Spare(first);
    }

    public Pin getFirst() {
        return first;
    }

    @Override
    public Score calculate(Score baseScore) {
        baseScore = baseScore.add(first.toScore());
        if(baseScore.isPending()) {
          baseScore = baseScore.add(first.toComplementedScore());
        }
        return baseScore;
    }

    @Override
    public Score getScore() {
        return Score.ofSpare();
    }

    @Override
    public boolean isFrameFinish() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spare spare = (Spare) o;
        return Objects.equals(first, spare.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first);
    }
}
