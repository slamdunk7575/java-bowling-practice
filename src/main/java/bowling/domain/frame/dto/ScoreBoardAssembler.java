package bowling.domain.frame.dto;

import bowling.domain.frame.Frame;
import bowling.domain.frame.ScoreBoard;
import bowling.domain.user.dto.UserAssembler;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardAssembler {

    private ScoreBoardAssembler() {
    }

    public static ScoreBoardDTO assemble(ScoreBoard scoreBoard) {
        List<Frame> frames = new ArrayList<>();
        Frame currentFrame = scoreBoard.getFirstFrame();
        frames.add(currentFrame);

        while (currentFrame.hasNext()) {
            currentFrame = currentFrame.next();
            frames.add(currentFrame);
        }

        return new ScoreBoardDTO(UserAssembler.assemble(scoreBoard.getUser()), frames);
    }


}
