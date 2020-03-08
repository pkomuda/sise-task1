public class Puzzle {

    private int height;
    private int width;
    private int[][] tiles;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setTile(int height, int width, int value){
        tiles[height][width] = value;
    }

    public Puzzle(int height, int width){
        this.height = height;
        this.width = width;
        this.tiles = new int[height][width];
    }

    public Puzzle(Puzzle other) {
        this.height = other.getHeight();
        this.width = other.getWidth();
        this.tiles = other.getTiles();
    }

    public int[] getZeroCoordinates() {
        int[] coordinates = new int[2];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (getTiles()[i][j] == 0) {
                    coordinates[0] = j;
                    coordinates[1] = i;
                }
            }
        }
        return coordinates;
    }

    public boolean canMove(char direction) {
        boolean ability = true;
        switch(direction) {
            case 'l':
                if (getZeroCoordinates()[0] == 0) {
                    ability = false;
                }
                break;
            case 'r':
                if (getZeroCoordinates()[0] == getWidth() - 1) {
                    ability = false;
                }
                break;
            case 'u':
                if (getZeroCoordinates()[1] == 0) {
                    ability = false;
                }
                break;
            case 'd':
                if (getZeroCoordinates()[0] == getHeight() - 1) {
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
        int x = getZeroCoordinates()[0];
        int y = getZeroCoordinates()[1];
        switch(direction) {
            case 'l':
                swapTiles(x, y, x - 1, y);
                break;
            case 'r':
                swapTiles(x, y, x + 1, y);
                break;
            case 'u':
                swapTiles(x, y, x, y - 1);
                break;
            case 'd':
                swapTiles(x, y, x, y + 1);
                break;
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
}
