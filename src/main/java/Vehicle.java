import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int index;
    private int currentX_c;
    private int currentY_r;
    private Ride currentRide;
    private Integer currentMin;
    private List<Ride> previousRides = new ArrayList<>();
    private boolean isNoLongerInUse = false;

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

    public void takeRide(List<Ride> rideList, int currStep, int stepsLeft, int bonus) {
        if (currentRide != null) {
            return;
        }

        Ride bestRide = null;
        int currLowest = 0;
        for (Ride ride : rideList) {
            if (ride.isFinished()) {
                continue;
            }

            final int distanceToStart = getDistanceBetweenCurrentPositionAndStartOfRide(ride.getX_start(), ride.getY_start());
            final int waitSteps = Math.max(0, ride.getEarliestStart() - currStep - distanceToStart);
            final int distancePlusWait = distanceToStart + waitSteps;

            int timeToFinishRide = distancePlusWait + ride.getDistance();

            if ((bestRide == null || currLowest > distancePlusWait)
                    && timeToFinishRide < stepsLeft
                    && timeToFinishRide < ride.getLatestFinish() - currStep
                    &&  (!ride.isTaken() || (ride.getVehicle().getCurrentMin() > distancePlusWait)) ) {
                bestRide = ride;
                currLowest = distancePlusWait;
                if ((ride.getEarliestStart() <= currStep + distanceToStart)) {
                    currLowest -= bonus;
                }
            }

        }
        if (bestRide != null) {
            bestRide.setTaken(true);
            bestRide.releaseVehicle();
            bestRide.setVehicle(this);
            currentRide = bestRide;
            currentMin = currLowest;
           // rideList.remove(bestRide);
//                      System.out.println("car " + index + " is assigned ride " + bestRide.getIndex());
//                System.out.println("(" + bestRide.getX_start() + "," + bestRide.getY_start() + ")");
//                System.out.println("becuase min is " + lowest);
        }else{
            setIsNoLongerInUse();
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

    public void nextMove(int currentStep) {
        if (currentRide == null || (currentRide.isActive() && currentRide.getEarliestStart() >= currentStep)) {
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
            this.currentRide.setFinished(true);
            this.currentRide = null;
            this.currentMin = null;
        }
    }

    public Integer getCurrentMin() {
        return currentMin;
    }

    public void setCurrentMin(final Integer currentMin) {
        this.currentMin = currentMin;
    }

    public void setIsNoLongerInUse(){
        isNoLongerInUse = true;
    }

    public boolean isNoLongerInUse(){
        return isNoLongerInUse;
    }
}
