public class DFSStrategy extends StrategyTemplate {
    private int MAX_DEPTH = 20;
    public DFSStrategy(SolutionState state){ super(state);}

    @Override
    public void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());
        while (toVisit.size() > 0) {
            Puzzle checkedState = toVisit.pop();
            if (checkedState.getDepth() > MAX_DEPTH){ continue; }
            if (checkedState.equals(getSolved())) {
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
                System.out.println(previousMoves);
                break;
            }

        }
    }
}
