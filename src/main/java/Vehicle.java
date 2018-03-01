public class Vehicle {
    private int currentX_c;
    private int currentY_r;

    public Vehicle(int currentX_c, int currentY_r) {
        this.currentX_c = currentX_c;
        this.currentY_r = currentY_r;
    }

    public Vehicle() {
        this.currentX_c = 0;
        this.currentY_r = 0;
    }

    public int getCurrentX_c() {
        return currentX_c;
    }

    public void setCurrentX_c(int currentX_c) {
        this.currentX_c = currentX_c;
    }

    public int getCurrentY_r() {
        return currentY_r;
    }

    public void setCurrentY_r(int currentY_r) {
        this.currentY_r = currentY_r;
    }
}
