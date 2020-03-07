import lombok.Getter;
import lombok.Setter;

public abstract class StrategyTemplate {

    @Getter @Setter
    private PuzzleState puzzleState;

    public StrategyTemplate(PuzzleState state){
        this.puzzleState = state;
    }

    abstract void algorithm();

    public final void runStrategy(){
        long startTime = System.nanoTime();
        algorithm();
        puzzleState.setCalculationTime((System.nanoTime() - startTime) / 1000000);
    }
}
