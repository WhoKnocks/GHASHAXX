import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int index;
    private int currentX_c;
    private int currentY_r;
    private Ride currentRide;
    private List<Ride> previousRides = new ArrayList<>();

    public Vehicle(int index, int currentX_c, int currentY_r) {
        this.index = index;
        this.currentX_c = currentX_c;
        this.currentY_r = currentY_r;
    }

    public Vehicle(int index) {
        this.index = index;
        this.currentX_c = 0;
        this.currentY_r = 0;
    }

    public void takeRide(List<Ride> rideList) {
        if (currentRide != null) {
            return;
        }

        Ride bestRide = null;
        int lowest = 0;
        for (Ride ride : rideList) {
            int newMin = Math.max(ride.getEarliestStart(), getDistanceBetweenCurrentPositionAndStartOfRide(ride.getX_start(), ride.getY_start()));
            //System.out.println("test " + newMin);
            //System.out.println();
            if (!ride.isTaken() && (bestRide == null || lowest > newMin)) {
                bestRide = ride;
                lowest = newMin;
            }
        }
        if (bestRide != null) {
            bestRide.setTaken(true);
            currentRide = bestRide;
            rideList.remove(bestRide);
//                System.out.println("car " + index + " is assigned ride " + bestRide.getIndex());
//                System.out.println("(" + bestRide.getX_start() + "," + bestRide.getY_start() + ")");
//                System.out.println("becuase min is " + lowest);
        }

    }

    public void addRide(Ride ride) {
        this.previousRides.add(ride);
    }

    public List<Ride> getPreviousRides() {
        return previousRides;
    }

    public void setPreviousRides(List<Ride> previousRides) {
        this.previousRides = previousRides;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }

    public void setCurrentRide(Ride currentRide) {
        this.currentRide = currentRide;
    }

    public int getDistanceBetweenCurrentPositionAndStartOfRide(int startOfRideX, int startOfRideY) {
        return Math.abs(currentX_c - startOfRideX) + Math.abs(currentY_r - startOfRideY);
    }

    public void nextMove() {
        if (currentRide == null) {
            return;
        }

        int toX = currentRide.isActive() ? currentRide.getX_finish() : currentRide.getX_start();
        int toY = currentRide.isActive() ? currentRide.getY_finish() : currentRide.getY_start();

        if (getCurrentX_c() < toX) {
            setCurrentX_c(getCurrentX_c() + 1);
        } else if (getCurrentX_c() > toX) {
            setCurrentX_c(getCurrentX_c() - 1);
        } else if (getCurrentY_r() < toY) {
            setCurrentY_r(getCurrentY_r() + 1);
        } else if (getCurrentY_r() > toY) {
            setCurrentY_r(getCurrentY_r() - 1);
        }

        if (!currentRide.isActive()) {
            if (getCurrentY_r() == toY && getCurrentX_c() == toX) {
                currentRide.setActive(true);
            }
        }
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

    public void checkIsAtDestination() {
        if (currentRide == null) return;
        if (currentRide.isActive() && currentRide.getX_finish() == currentX_c && currentRide.getY_finish() == currentY_r) {
            this.previousRides.add(currentRide);
            this.currentRide = null;
        }
    }
}
