import lombok.Data;

import java.util.ArrayDeque;
import java.util.Arrays;

public @Data class Puzzle {

    private int height;
    private int width;
    private int[][] tiles;
    private ArrayDeque<Puzzle> possiblePuzzles = new ArrayDeque<>();
    private Puzzle previousPuzzle = null;
    private char previousMove;
    private int depth;


    public void setTile(int height, int width, int value){
        tiles[height][width] = value;
    }

    public Puzzle(int height, int width){
        this.height = height;
        this.width = width;
        this.tiles = new int[height][width];
        this.depth = 0;
    }

    public Puzzle(Puzzle other, char direction) {
        this.height = other.getHeight();
        this.width = other.getWidth();
        this.tiles = Arrays.stream(other.getTiles()).map(int[]::clone).toArray(int[][]::new);
        this.previousPuzzle = other;
        this.previousMove = direction;
        this.depth = other.depth + 1;
    }

    public int[] indexOf(int value) {
        int[] coordinates = new int[2];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (getTiles()[i][j] == value) {
                    coordinates[0] = i;
                    coordinates[1] = j;
                }
            }
        }
        return coordinates;
    }

    public boolean canMove(char direction) {
        boolean ability = true;
        switch(direction) {
            case 'L':
                if (indexOf(0)[1] == 0) {
                    ability = false;
                }
                break;
            case 'R':
                if (indexOf(0)[1] == getWidth() - 1) {
                    ability = false;
                }
                break;
            case 'U':
                if (indexOf(0)[0] == 0) {
                    ability = false;
                }
                break;
            case 'D':
                if (indexOf(0)[0] == getHeight() - 1) {
                    ability = false;
                }
                break;
            default:
                ability = false;
                break;
        }
        return ability;
    }

    public void swapTiles(int x1, int y1, int x2, int y2) {
        int temp = getTiles()[y1][x1];
        this.setTile(y1, x1, getTiles()[y2][x2]);
        this.setTile(y2, x2, temp);
    }

    public void move(char direction) {
        int x = indexOf(0)[1];
        int y = indexOf(0)[0];
        switch(direction) {
            case 'L':
                swapTiles(x, y, x - 1, y);
                break;
            case 'R':
                swapTiles(x, y, x + 1, y);
                break;
            case 'U':
                swapTiles(x, y, x, y - 1);
                break;
            case 'D':
                swapTiles(x, y, x, y + 1);
                break;
        }
    }

    public void generatePuzzles(String order) {
        for (char direction : order.toCharArray()) {
            if (canMove(direction)) {
                Puzzle other = new Puzzle(this, direction);
                other.move(direction);
                possiblePuzzles.add(other);
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < height;i++){
            for(int j = 0;j < width;j++){
                sb.append(String.format("%3s",tiles[i][j]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != getClass()) return false;
        Puzzle other = (Puzzle) obj;
        return Arrays.deepEquals(getTiles(),other.getTiles());
    }
}
