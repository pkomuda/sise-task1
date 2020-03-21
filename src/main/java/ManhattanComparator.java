import java.util.Comparator;

public class ManhattanComparator implements Comparator<Puzzle> {

    private int checkDistance(Puzzle currentState) {
        int distance = 0;
        for (int i = 0; i < currentState.getHeight(); i++) {
            for (int j = 0; j < currentState.getWidth(); j++) {
                if (currentState.getTiles()[j][i] != StrategyTemplate.solved.getTiles()[j][i]) {
                    int xSolved = StrategyTemplate.solved.indexOf(StrategyTemplate.solved.getTiles()[j][i])[0];
                    int ySolved = StrategyTemplate.solved.indexOf(StrategyTemplate.solved.getTiles()[j][i])[1];
                    distance += Math.abs(j - xSolved) + Math.abs(i - ySolved); //suma ruchow potrzebnych zeby przesunac kazdy klocek na swoje miejsce
                }
            }
        }
        return distance;
    }

    @Override
    public int compare(Puzzle o1, Puzzle o2) {
        return Integer.compare(checkDistance(o1), checkDistance(o2));
    }
}
