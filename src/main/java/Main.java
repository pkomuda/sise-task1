import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Puzzle puzzle = FileOperations.loadPuzzle("txtFiles/input.txt");

        PuzzleState state = new PuzzleState(puzzle);
        System.out.println(state.getPuzzle());
        puzzle.move('r');
        System.out.println(puzzle);
        puzzle.move('d');
        System.out.println(puzzle);


    }
}
