import java.util.*;

public class AStarStrategy extends StrategyTemplate {

    private String additionalParameter;
    protected ArrayDeque<Puzzle> toVisit;

    public AStarStrategy(SolutionState state, String solutionFilepath, String statsFilepath, String additionalParameter) {
        super(state,solutionFilepath,statsFilepath);
        this.additionalParameter = additionalParameter;
        this.toVisit = new ArrayDeque<>();
    }

    private int checkDistance(Puzzle currentState) {
        int distance = 0;
        if (additionalParameter.equals("hamm")){
            for (int i = 0; i < currentState.getHeight(); i++) {
                for (int j = 0; j < currentState.getWidth(); j++) {
                    if (currentState.getTiles()[i][j] != getSolved().getTiles()[i][j]) {
                        distance ++; //ilosc klockow nie na swoim miejscu
                    }
                }
            }
        } else if (additionalParameter.equals("manh")) {
            for (int i = 0; i < currentState.getHeight(); i++) {
                for (int j = 0; j < currentState.getWidth(); j++) {
                    if (currentState.getTiles()[j][i] != getSolved().getTiles()[j][i]) { //tutaj nie jestem pewien czy kolejnosc j,i jest dobrze xd
                        int xSolved = getSolved().indexOf(getSolved().getTiles()[j][i])[0];
                        int ySolved = getSolved().indexOf(getSolved().getTiles()[j][i])[1];
                        distance += Math.abs(j - xSolved) + Math.abs(i - ySolved); //suma ruchow potrzebnych zeby przesunac kazdy klocek na swoje miejsce
                    }
                }
            }
        }
        return distance;
    }

    private Puzzle bestUnvisitedPuzzle(ArrayDeque<Puzzle> possiblePuzzles, ArrayDeque<Puzzle> visited) {
        Map<Puzzle, Integer> puzzlesAndHeuristics = new HashMap<>();
        for (Puzzle p : possiblePuzzles) {
            if (!visited.contains(p)) {
                puzzlesAndHeuristics.put(p, checkDistance(p));
            }
        }
        int minHeuristic = Collections.min(puzzlesAndHeuristics.values());
        return puzzlesAndHeuristics.keySet()
                .stream()
                .filter(puzzle -> puzzlesAndHeuristics.get(puzzle) == minHeuristic)
                .findFirst().get();
    }

    @Override
    void algorithm(String order) {
        toVisit.add(getSolutionState().getPuzzle());
        Puzzle checkedState = null;
        while(toVisit.size() > 0) {
            checkedState = toVisit.pop();
            adjustMaxDepth(checkedState);
            visited.add(checkedState);
            if (checkedState.equals(getSolved())) {
                System.out.println(checkedState);
                getSolutionState().setSolutionFound(true);
                System.out.println(previousMoves);
                break;
            }
            checkedState.generatePuzzles("UDRL"); //tutaj chyba musi byc zhardkodowana jakas kolejnosc bo nie podajemy jej w argumentach wywolania, nie powinno miec wiekszego znaczenia jaka ona bedzie
            toVisit.add(Objects.requireNonNull(bestUnvisitedPuzzle(checkedState.getPossiblePuzzles(), visited)));
        }
        String moveHistory = getMoveHistory(checkedState);
        serializeSolution(moveHistory);
    }
}
