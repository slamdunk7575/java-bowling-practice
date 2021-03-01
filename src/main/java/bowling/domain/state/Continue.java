package bowling.domain.state;

import bowling.domain.frame.Remaining;
import bowling.domain.frame.Score;
import bowling.domain.pin.Pin;

public class Continue implements State {

    private final Pin first;

    public Continue(Pin first) {
        this.first = first;
    }

    public static State of(Pin felledPin) {
        return new Continue(felledPin);
    }

    @Override
    public State bowl(Pin felledPin) {
        return null;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Score calculate(Score baseScore) {
        return baseScore.add(first.toScore());
    }

    @Override
    public Score getScore() {
        return first.toScore(Remaining.CONTINUE);
    }

    @Override
    public boolean isFrameFinish(State state) {
        return false;
    }
}
