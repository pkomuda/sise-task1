import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String chosenStrat = args[0];
        String additionalParameter = args[1];
        String inputFilename = args[2];
        String solutionFilename = args[3];
        String statsFilename = args[4];

        Puzzle puzzle = FileOperations.loadPuzzle(inputFilename);
        SolutionState state = new SolutionState(puzzle);
        StrategyTemplate strategyTemplate = null;
        switch (chosenStrat){
            case "bfs":{
                strategyTemplate = new BFSStrategy(state,solutionFilename,statsFilename);
            }
            case "dfs":{
                strategyTemplate = new DFSStrategy(state,solutionFilename,statsFilename);
            }
            case "astr":{
                // Tu pozostale jak juz beda
                if(additionalParameter == "hamm"){

                }
                if(additionalParameter == "manh"){

                }
            }
        }

        strategyTemplate.runStrategy(additionalParameter);

    }
}
