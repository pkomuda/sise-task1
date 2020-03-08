import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class PuzzleStateOperations {

    @Getter
    private List<PuzzleState> states = new ArrayList<>();

    public PuzzleStateOperations(PuzzleState initialState) {
        states.add(initialState);
    }

    public void generateStates(String directions) {
        for (int i = 0; i < directions.toCharArray().length; i++) {
            if (states.get(i).canMove(directions.charAt(i))) {

            }
        }
    }
}
