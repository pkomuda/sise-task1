import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayDeque;

@Getter @Setter
public abstract class StrategyTemplate {

    private SolutionState solutionState;
    private Puzzle solved;
    ArrayDeque<Puzzle> toVisit = new ArrayDeque<>();
    ArrayDeque<Puzzle> visited = new ArrayDeque<>();
    protected String previousMoves = "";

    public StrategyTemplate(SolutionState state)
    {
        this.solutionState = state;
        solved = new Puzzle(state.getPuzzle().getHeight(),state.getPuzzle().getWidth());
        int height = solved.getHeight();
        int width = solved.getWidth();
        for(int i = 0; i < height; i++)
            for (int j = 0; j < width; j++){
                solved.setTile(i,j,1+j+(i*width));
            }
        solved.setTile(height-1,width-1,0);
    }

    abstract void algorithm(String order);

    public final void runStrategy(String order){
        long startTime = System.nanoTime();
        algorithm(order);
        solutionState.setCalculationTime((System.nanoTime() - startTime) / 1000000);
        try {
            FileOperations.saveSolutionState("txtFiles/stats",getSolutionState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void solutionMoves(Puzzle checkedState) {
        Puzzle newPuzzle = checkedState;
        previousMoves += newPuzzle.getPreviousMove();
        while (newPuzzle.getPreviousPuzzle() != null) {
            newPuzzle = newPuzzle.getPreviousPuzzle();
            previousMoves += newPuzzle.getPreviousMove();
        }
        previousMoves = previousMoves.substring(0, previousMoves.length() - 1);
        StringBuilder sb = new StringBuilder(previousMoves);
        sb.reverse();
        solutionState.setSolutionLength(previousMoves.length());
        sb.insert(0, previousMoves.length() + "\n");
        try {
            FileOperations.saveSolutionMoves("txtFiles/solution.txt", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
