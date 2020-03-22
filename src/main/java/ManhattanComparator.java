import java.util.Comparator;

public class ManhattanComparator implements Comparator<Puzzle> {

    private int checkDistance(Puzzle currentState) {
        int distance = 0;
        for (int i = 0; i < currentState.getHeight(); i++) {    //numer wiersza
            for (int j = 0; j < currentState.getWidth(); j++) { // numer kolumny
                if (currentState.getTiles()[i][j] != StrategyTemplate.solved.getTiles()[i][j]) {
                    int solved0 = StrategyTemplate.solved.indexOf(StrategyTemplate.solved.getTiles()[i][j])[0]; // 0 - kolumna
                    int solved1 = StrategyTemplate.solved.indexOf(StrategyTemplate.solved.getTiles()[i][j])[1]; // 1 - wiersz
                    distance += Math.abs(j - solved0) + Math.abs(i - solved1); //suma ruchow potrzebnych zeby przesunac kazdy klocek na swoje miejsce
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
