import java.util.List;

public class Ride {
    private int index;
    private int x_start;
    private int x_finish;
    private int y_start;
    private int y_finish;
    private int earliestStart;
    private int latestFinish;
    private boolean isTaken;
    private boolean isActive;

    public Ride(int index, int x_start, int y_start, int x_finish, int y_finish, int earliestStart, int lastestFinish) {
        this.index = index;
        this.x_start = x_start;
        this.x_finish = x_finish;
        this.y_start = y_start;
        this.y_finish = y_finish;
        this.earliestStart = earliestStart;
        this.latestFinish = lastestFinish;
        isTaken = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getX_start() {
        return x_start;
    }

    public void setX_start(int x_start) {
        this.x_start = x_start;
    }

    public int getX_finish() {
        return x_finish;
    }

    public void setX_finish(int x_finish) {
        this.x_finish = x_finish;
    }

    public int getY_start() {
        return y_start;
    }

    public void setY_start(int y_start) {
        this.y_start = y_start;
    }

    public int getY_finish() {
        return y_finish;
    }

    public void setY_finish(int y_finish) {
        this.y_finish = y_finish;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setEarliestStart(int earliestStart) {
        this.earliestStart = earliestStart;
    }

    public int getLatestFinish() {
        return latestFinish;
    }

    public void setLatestFinish(int latestFinish) {
        this.latestFinish = latestFinish;
    }

    public int getDistance() {
        return Math.abs(getX_start() - getX_finish()) + Math.abs(getY_start() - getY_finish());
    }


}
