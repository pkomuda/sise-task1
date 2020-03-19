import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayDeque;

@Getter @Setter
public abstract class StrategyTemplate {

    private SolutionState solutionState;
    private Puzzle solved;
    protected ArrayDeque<Puzzle> visited = new ArrayDeque<>();
    protected String previousMoves = "";
    private String solutionFilepath;
    private String statsFilepath;
    public StrategyTemplate(SolutionState state, String solutionFilepath, String statsFilepath)
    {
        this.solutionState = state;
        this.solutionFilepath = solutionFilepath;
        this.statsFilepath = statsFilepath;
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
            FileOperations.saveSolutionState(statsFilepath,getSolutionState());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getMoveHistory(Puzzle checkedState) {
        Puzzle newPuzzle = checkedState;
        previousMoves += newPuzzle.getPreviousMove();
        while (newPuzzle.getPreviousPuzzle() != null) {
            newPuzzle = newPuzzle.getPreviousPuzzle();
            previousMoves += newPuzzle.getPreviousMove();
        }
        previousMoves = previousMoves.substring(0, previousMoves.length() - 1);
        solutionState.setSolutionLength(previousMoves.length());
        return previousMoves;
    }

    public void serializeSolution(String previousMoves){
        checkSolutionFound();
        StringBuilder sb = new StringBuilder(previousMoves);
        sb.reverse();
        sb.insert(0, getSolutionState().getSolutionLength() + "\n");
        try {
            FileOperations.saveSolutionMoves(solutionFilepath, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void adjustMaxDepth(Puzzle checkedState){
        if (checkedState.getDepth() > getSolutionState().getMaxDepthReached()){
            getSolutionState().setMaxDepthReached(checkedState.getDepth());
        }
    }

    public void checkSolutionFound(){
        if(!getSolutionState().isSolutionFound()){
            getSolutionState().setSolutionLength(-1);
        }
    }
}
