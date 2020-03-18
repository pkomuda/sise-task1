import java.util.ArrayDeque;
import java.util.Stack;

public class DFSStrategy extends StrategyTemplate {
    private int MAX_DEPTH = 20;
    protected Stack<Puzzle> toVisit;
    public DFSStrategy(SolutionState state){
        super(state);
        this.toVisit = new Stack<>();
    }

    @Override
    public void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());
        Puzzle checkedState = null;
        while (toVisit.size() > 0) {
            checkedState = toVisit.pop();
            if (checkedState.getDepth() > getSolutionState().getMaxDepthReached()){
                getSolutionState().setMaxDepthReached(checkedState.getDepth());
                if (checkedState.getDepth() > MAX_DEPTH){ continue; }
            }
            if (checkedState.equals(getSolved())) {
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
                System.out.println(previousMoves);
                break;
            }
            visited.add(checkedState);
            checkedState.generatePuzzles(order);

            for (Puzzle p : checkedState.getPossiblePuzzles()) {
                if (!visited.contains(p)) {
                    toVisit.add(p);
                    System.out.println(p);
                }
            }




        }
        if(!getSolutionState().isSolutionFound()){
            getSolutionState().setSolutionLength(-1);
        }
        solutionMoves(checkedState);
    }
}
