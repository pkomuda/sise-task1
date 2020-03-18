import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String chosenStrat = args[0];
        String additionalParameter = args[1];
        String inputFilename = args[2];
        String solutionFilename = args[3];
        String statsFilename = args[4];



        Puzzle puzzle = FileOperations.loadPuzzle(inputFilename);

        SolutionState state = new SolutionState(puzzle);

        DFSStrategy dfs = new DFSStrategy(state,solutionFilename,statsFilename);
        dfs.runStrategy(additionalParameter);

    }
}
