public class Puzzle {

    private int height;
    private int width;
    private int tiles[][];

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
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
        tiles = new int[height][width];
    }
}
