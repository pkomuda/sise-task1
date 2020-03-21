import java.util.ArrayDeque;

public class BFSStrategy extends StrategyTemplate {
    protected ArrayDeque<Puzzle> toVisit;
    public BFSStrategy(SolutionState state,String solutionFilepath,String statsFilepath) {
        super(state,solutionFilepath,statsFilepath);
        this.toVisit = new ArrayDeque<>();
    }

    @Override
    public void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());
        Puzzle checkedState = null;
        while (toVisit.size() > 0) {
            checkedState = toVisit.pop();
            adjustMaxDepth(checkedState);
            visited.add(checkedState);
            if (checkedState.equals(StrategyTemplate.solved)) {
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
                System.out.println(previousMoves);
                break;
            }
            checkedState.generatePuzzles(order);
            for (Puzzle p : checkedState.getPossiblePuzzles()) {
                if (!visited.contains(p)) {
                    toVisit.add(p);
                }
            }
        }
        String moveHistory = getMoveHistory(checkedState);
        serializeSolution(moveHistory);
    }
}
