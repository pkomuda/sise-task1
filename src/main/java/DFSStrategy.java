import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSStrategy extends StrategyTemplate {
    private int MAX_DEPTH = 20;
    protected Stack<Puzzle> toVisit;
    public DFSStrategy(SolutionState state,String solutionFilepath,String statsFilepath){
        super(state,solutionFilepath,statsFilepath);
        this.toVisit = new Stack<>();
    }

    @Override
    public void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());
        Puzzle checkedState = null;
        while (toVisit.size() > 0) {
            checkedState = toVisit.pop();
            adjustMaxDepth(checkedState);
            if (checkedState.getDepth() > MAX_DEPTH){ continue; }
            if (checkedState.equals(StrategyTemplate.solved)) {
                getSolutionState().setSolutionFound(true);
                break;
            }
            visited.add(checkedState);
            checkedState.generatePuzzles(order);
            List<Puzzle> tempPuzzles = new ArrayList<>(checkedState.getPossiblePuzzles());
            Collections.reverse(tempPuzzles);
            for (Puzzle p : tempPuzzles) {
                if (!visited.contains(p)) {
                    toVisit.add(p);
                }
            }
        }
        String moveHistory = getMoveHistory(checkedState);
        serializeSolution(moveHistory);
    }
}
