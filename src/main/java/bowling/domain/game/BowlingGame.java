package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.frame.Index;
import bowling.domain.frame.ScoreBoard;
import bowling.domain.pin.Pin;
import bowling.domain.user.User;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class BowlingGame {

    private final List<ScoreBoard> scoreBoards;

    private BowlingGame(List<ScoreBoard> scoreBoards) {
        this.scoreBoards = scoreBoards;
    }

    public static BowlingGame of(List<String> users) {
        return users.stream()
                .map(User::of)
                .map(user -> ScoreBoard.init(user, Frames.init()))
                .collect(collectingAndThen(toList(), BowlingGame::new));
    }

    public void bowl(Pin felledPin) {
        getCurrent().bowl(felledPin);
    }

    private ScoreBoard getCurrent() {
        return scoreBoards.stream()
                .filter(scoreBoard -> !scoreBoard.isGameOver())
                .min(Comparator.comparing(ScoreBoard::getLastIndex, Comparator.comparing(Index::getIndex)))
                .orElseThrow(() -> new IllegalArgumentException("모든 게임이 종료 되었습니다."));
    }

    public boolean isGameOver() {
        return scoreBoards.stream()
                .map(ScoreBoard::isGameOver)
                .reduce((b1, b2) -> b1 && b2)
                .orElseThrow(IllegalArgumentException::new);
    }

    public User getCurrentUser() {
        return getCurrent().getUser();
    }

}
