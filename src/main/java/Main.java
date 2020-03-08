import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Puzzle puzzle = FileOperations.loadPuzzle("txtFiles/input.txt");
        PuzzleState state = new PuzzleState(puzzle);
        System.out.println(state.getPuzzle());
        System.out.println(state.move('r').getPuzzle());
        System.out.println(state.move('d').getPuzzle());
    }
}
