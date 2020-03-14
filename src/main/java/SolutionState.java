import lombok.Data;

public @Data class SolutionState {

    private long calculationTime;
    private boolean solutionFound = false;
    private int maxDepthReached;
    private int solutionLength;
    private Puzzle puzzle;

    public SolutionState(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.maxDepthReached = 0;
        this.solutionLength = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(solutionLength);
        sb.append("\n");
        sb.append(maxDepthReached);
        sb.append("\n");
        sb.append(calculationTime);
        return sb.toString();
    }
}
