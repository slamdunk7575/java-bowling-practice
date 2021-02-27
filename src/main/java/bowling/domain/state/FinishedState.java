package bowling.domain.state;

import bowling.domain.pin.Pin;

public abstract class FinishedState implements State {

    @Override
    public State bowl(Pin felledPin) {
        throw new IllegalArgumentException("현재 상태는 더이상 볼링공을 굴릴 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
