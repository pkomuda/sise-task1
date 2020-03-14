import java.io.IOException;

public class BFSStrategy extends StrategyTemplate {

    public BFSStrategy(SolutionState state) {
        super(state);
    }

    @Override
    public void algorithm(String order){
        toVisit.add(getSolutionState().getPuzzle());
        Puzzle checkedState = null;
        while(toVisit.size() > 0){
            checkedState = toVisit.pop();
            if (checkedState.getDepth() > getSolutionState().getMaxDepthReached()){
                getSolutionState().setMaxDepthReached(checkedState.getDepth());
            }
            visited.add(checkedState);
            if (checkedState.equals(getSolved())) {
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
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
        if(!getSolutionState().isSolutionFound()){
            getSolutionState().setSolutionLength(-1);
        }
        solutionMoves(checkedState);

    }
}
