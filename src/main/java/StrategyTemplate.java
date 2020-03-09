import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;


@Getter @Setter
public abstract class StrategyTemplate {

    private SolutionState solutionState;
    private Puzzle solved = new Puzzle(4,4); // temp, trzeba będzie generować w zaleznosci od wielkosci
    ArrayDeque<Puzzle> toVisit = new ArrayDeque<>();
    ArrayDeque<Puzzle> visited = new ArrayDeque<>();
    public StrategyTemplate(SolutionState state)
    {
        this.solutionState = state;
        int height = solved.getHeight();
        int width = solved.getWidth();
        for(int i = 0; i < height; i++)
            for (int j = 0; j < width; j++){
                solved.setTile(i,j,1+j+(i*width));
            }
        solved.setTile(height-1,width-1,0);
    }

    abstract void algorithm(String order);

    public final void runStrategy(String order){
        long startTime = System.nanoTime();
        algorithm(order);
        solutionState.setCalculationTime((System.nanoTime() - startTime) / 1000000);
    }
}
