import java.util.*;

public class AStarStrategy extends StrategyTemplate {

    protected PriorityQueue<Puzzle> toVisit;

    public AStarStrategy(SolutionState state, String solutionFilepath, String statsFilepath, String additionalParameter) {
        super(state,solutionFilepath,statsFilepath);
        if (additionalParameter.equals("hamm")) {
            this.toVisit = new PriorityQueue<>(new HammingComparator());
        } else if (additionalParameter.equals("manh")) {
            this.toVisit = new PriorityQueue<>(new ManhattanComparator());
        }
    }

    @Override
    void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());
        Puzzle checkedState = null;
        while(toVisit.size() > 0) {
            checkedState = toVisit.poll();
            adjustMaxDepth(checkedState);
            visited.add(checkedState);
            if (checkedState.equals(StrategyTemplate.solved)) {
                getSolutionState().setSolutionFound(true);
                break;
            }
            checkedState.generatePuzzles("UDRL"); //tutaj chyba musi byc zhardkodowana jakas kolejnosc bo nie podajemy jej w argumentach wywolania, nie powinno miec wiekszego znaczenia jaka ona bedzie
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
