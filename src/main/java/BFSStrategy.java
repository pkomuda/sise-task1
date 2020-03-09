public class BFSStrategy extends StrategyTemplate {

    public BFSStrategy(SolutionState state) {
        super(state);
    }

    @Override
    public void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());

        while(toVisit.size() > 0){
            Puzzle checkedState = toVisit.pop();
            visited.add(checkedState);
            if (checkedState.equals(getSolved())) {
                getSolutionState().setSolutionFound(true);
                break;
            }
            checkedState.generatePuzzles(order);
            for (Puzzle p : checkedState.getPossiblePuzzles()) {
                if (!visited.contains(p)) {
                    toVisit.add(p);
                }
            }
        }
    }
}
