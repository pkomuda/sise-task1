import lombok.Data;

public @Data class PuzzleState {

    private long calculationTime;
    private int visitedStatesCount;
    private int computedStatesCount;
    private int maxDepthReached;
    private int solutionLength;
    private String directionsOrder;
    private Puzzle puzzle;

    public PuzzleState(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.visitedStatesCount = 0;
        this.computedStatesCount = 0;
        this.maxDepthReached = 0;
        this.solutionLength = 0;
    }

    public int[] getZeroCoordinates() {
        int[] coordinates = new int[2];
        for (int i = 0; i < puzzle.getHeight(); i++) {
            for (int j = 0; j < puzzle.getWidth(); j++) {
                if (puzzle.getTiles()[i][j] == 0) {
                    coordinates[0] = j;
                    coordinates[1] = i;
                }
            }
        }
        return coordinates;
    }

    public boolean canMove(char direction) {
        boolean ability = true;
        switch(direction) {
            case 'l':
                if (getZeroCoordinates()[0] == 0) {
                    ability = false;
                }
                break;
            case 'r':
                if (getZeroCoordinates()[0] == puzzle.getWidth() - 1) {
                    ability = false;
                }
                break;
            case 'u':
                if (getZeroCoordinates()[1] == 0) {
                    ability = false;
                }
                break;
            case 'd':
                if (getZeroCoordinates()[0] == puzzle.getHeight() - 1) {
                    ability = false;
                }
                break;
            default:
                ability = false;
                break;
        }
        return ability;
    }

    public Puzzle swapTiles(int x1, int y1, int x2, int y2) {
        Puzzle swapped = new Puzzle(puzzle);
        int temp = puzzle.getTiles()[y1][x1];
        swapped.setTile(y1, x1, puzzle.getTiles()[y2][x2]);
        swapped.setTile(y2, x2, temp);
        return swapped;
    }

    public PuzzleState move(char direction) {
        int x = getZeroCoordinates()[0];
        int y = getZeroCoordinates()[1];
        switch(direction) {
            case 'l':
                return new PuzzleState(swapTiles(x, y, x - 1, y));
            case 'r':
                return new PuzzleState(swapTiles(x, y, x + 1, y));
            case 'u':
                return new PuzzleState(swapTiles(x, y, x, y - 1));
            case 'd':
                return new PuzzleState(swapTiles(x, y, x, y + 1));
            default:
                return null;
        }
    }
}
