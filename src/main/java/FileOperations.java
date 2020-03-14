import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOperations {

    static public Puzzle loadPuzzle(String filepath) throws java.io.IOException{
        final FileReader fileReader = new FileReader(filepath);
        final CSVParser parser = new CSVParserBuilder()
                        .withSeparator(' ')
                        .build();
        final CSVReader csvReader = new CSVReaderBuilder(fileReader)
                        .withCSVParser(parser)
                        .build();
        List<String[]> lines = csvReader.readAll();
        final int height = Integer.parseInt(lines.get(0)[0]);
        final int width = Integer.parseInt(lines.get(0)[1]);
        Puzzle puzzle = new Puzzle(height, width);
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++){
                int value = Integer.parseInt(lines.get(i+1)[j]);
                puzzle.setTile(i,j,value);
            }
        }
        return puzzle;
    }

    static public void saveSolutionMoves(String filepath, String solutionMoves) throws IOException {
        final FileWriter fileWriter = new FileWriter(filepath);
        fileWriter.write(solutionMoves);
        fileWriter.close();
    }
}
