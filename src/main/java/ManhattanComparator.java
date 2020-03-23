import java.util.Comparator;

public class ManhattanComparator implements Comparator<Puzzle> {

    private int checkDistance(Puzzle currentState) {
        int distance = 0;
        for (int i = 0; i < currentState.getHeight(); i++) {
            for (int j = 0; j < currentState.getWidth(); j++) {
                if (currentState.getTiles()[i][j] != StrategyTemplate.solved.getTiles()[i][j]) {
                    int ySolved = StrategyTemplate.solved.indexOf(StrategyTemplate.solved.getTiles()[i][j])[0];
                    int xSolved = StrategyTemplate.solved.indexOf(StrategyTemplate.solved.getTiles()[i][j])[1];
                    distance += Math.abs(i - ySolved) + Math.abs(j - xSolved); //suma ruchow potrzebnych zeby przesunac kazdy klocek na swoje miejsce
                }
            }
        }
        return distance + currentState.getDepth();
    }

    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return Integer.compare(checkDistance(o1), checkDistance(o2));
    }
}
