public class Coordinates {

    private  int yPos;
    private int xPos;

    public Coordinates(int yPos, int xPos) {
        this.yPos = yPos;
        this.xPos = xPos;
    }

    public void setNewCoords(int yPos, int xPos) {
        this.yPos = yPos;
        this.xPos = xPos;
    }


    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }
    public int getxPos() {
        return xPos;
    }


}
