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


}
