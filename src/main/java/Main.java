import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Puzzle puzzle = FileOperations.loadPuzzle("txtFiles/input.txt");

        SolutionState state = new SolutionState(puzzle);
//        System.out.println(state.getPuzzle());
//        puzzle.move('R');
//        System.out.println(puzzle);
//        puzzle.move('D');
//        System.out.println(puzzle);

        BFSStrategy bfs = new BFSStrategy(state);
        bfs.runStrategy("RDUL");

//        System.out.println(puzzle);
//        System.out.println("GENERATED:");
//        puzzle.generatePuzzles("DRUL");
//        for (Puzzle p : puzzle.getPossiblePuzzles()) {
//            System.out.println(p);
//        }
    }
}
