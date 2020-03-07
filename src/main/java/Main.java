public class Main {

    public static void main(String[] args) throws Exception {
        Puzzle puzzle = FileOperations.loadPuzzle("txtFiles/input.txt");
        PuzzleState state = new PuzzleState(puzzle);
        System.out.println(state.getPuzzle());
        System.out.println(state.move('r').getPuzzle());
    }
}
