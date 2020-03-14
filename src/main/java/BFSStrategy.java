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
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
                solutionMoves(checkedState);
                System.out.println(previousMoves);
                break;
            }
            checkedState.generatePuzzles(order);
            for (Puzzle p : checkedState.getPossiblePuzzles()) {
                if (!visited.contains(p)) {
                    toVisit.add(p);
                    System.out.println(p);
                }
            }
        }
    }
}
