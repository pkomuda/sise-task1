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
            adjustMaxDepth(checkedState);
            if (checkedState.getDepth() > MAX_DEPTH){ continue; }
            if (checkedState.equals(getSolved())) {
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
                System.out.println(previousMoves);
                return;
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
        checkSolutionFound();
        solutionMoves(checkedState);
    }
}
