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
//        System.out.println(state.getPuzzle());
//        puzzle.move('R');
//        System.out.println(puzzle);
//        puzzle.move('D');
//        System.out.println(puzzle);
//
//        BFSStrategy bfs = new BFSStrategy(state);
//        bfs.runStrategy("RDUL");

        DFSStrategy dfs = new DFSStrategy(state);
        dfs.runStrategy(additionalParameter);

//        System.out.println(puzzle);
//        System.out.println("GENERATED:");
//        puzzle.generatePuzzles("DRUL");
//        for (Puzzle p : puzzle.getPossiblePuzzles()) {
//            System.out.println(p);
//        }
    }
}
