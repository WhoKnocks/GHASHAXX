public class Ride {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int earliestStart;
    private int lastestFinish;

    public Ride(int x1, int y1, int x2, int y2, int earliestStart, int lastestFinish) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.earliestStart = earliestStart;
        this.lastestFinish = lastestFinish;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public int getLastestFinish() {
        return lastestFinish;
    }

    public void setLastestFinish(int lastestFinish) {
        this.lastestFinish = lastestFinish;
    }

    public int getDistance(){
        return Math.abs(getX1()-getX2()) + Math.abs(getY1()-getY2());
    }
}
