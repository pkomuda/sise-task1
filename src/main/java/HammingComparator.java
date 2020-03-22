import java.util.Comparator;

public class HammingComparator implements Comparator<Puzzle> {

    private int checkDistance(Puzzle currentState) {
        int distance = 0;
        for (int i = 0; i < currentState.getHeight(); i++) {
            for (int j = 0; j < currentState.getWidth(); j++) {
                if (currentState.getTiles()[i][j] != StrategyTemplate.solved.getTiles()[i][j]) {
                    distance ++; //ilosc klockow nie na swoim miejscu
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
