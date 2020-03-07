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
