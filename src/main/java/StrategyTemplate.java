import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class StrategyTemplate {

    private SolutionState solutionState;
    private Puzzle solved = new Puzzle(4,4); // temp, trzeba będzie generować w zaleznosci od wielkosci

    public StrategyTemplate(SolutionState state)
    {
        this.solutionState = state;
        int height = solved.getHeight();
        int width = solved.getWidth();
        for(int i = 0; i < height; i++)
            for (int j = 0; j < width; j++){
                solved.setTile(i,j,(height*width-1)-j-(i*solved.getWidth()));
            }
    }

    abstract void algorithm();

    public final void runStrategy(){
        long startTime = System.nanoTime();
        algorithm();
        solutionState.setCalculationTime((System.nanoTime() - startTime) / 1000000);
    }
}
