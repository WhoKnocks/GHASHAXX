import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        run("a_example");
        run("b_should_be_easy");
        run("c_no_hurry");
        run("d_metropolis");
        run("e_high_bonus");
    }

    public static void run(String fileName) {
        List<List<Integer>> lines = IOUtil.getLines(fileName + ".in", " ", Integer::parseInt);
        List<Integer> firstRow = lines.get(0);

        int numberOfRows_R = firstRow.get(0);
        int numberOfColumns_C = firstRow.get(1);
        int numberVehicles_F = firstRow.get(2);
        int numberOfRides_N = firstRow.get(3);
        int perRideBonus_B = firstRow.get(4);
        int numberOfSteps_T = firstRow.get(5);

        int rideIndex = 0;
        List<Ride> allRides = new ArrayList<>();
        for (List<Integer> row : lines.subList(1, lines.size())) {
            allRides.add(new Ride(rideIndex++, row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)));
        }

        int meanStartingPoint_X = 0;
        int meanStartingPoint_Y = 0;
        for (Ride ride : allRides) {
            meanStartingPoint_X += ride.getX_start();
            meanStartingPoint_Y += ride.getY_start();
        }
        meanStartingPoint_X /= numberOfRides_N;
        meanStartingPoint_Y /= numberOfRides_N;
        int ignoreRidesFactor_X = (int) (0.97 * (numberOfRows_R / 2)) + meanStartingPoint_X;
        int ignoreRidesFactor_Y = (int) (0.97 * (numberOfColumns_C / 2)) + meanStartingPoint_Y;


        List<Vehicle> vehicles = VehicleBuilder.buildVehicles(numberVehicles_F);

        for (int i = 0; 0 <= numberOfSteps_T; numberOfSteps_T--, i++) {
            for (Vehicle vehicle : vehicles) {
                vehicle.takeRide(allRides, i, numberOfSteps_T, ignoreRidesFactor_X, ignoreRidesFactor_Y);
            }
            for (Vehicle vehicle : vehicles) {
                vehicle.nextMove(i);

            }
            vehicles.forEach(Vehicle::checkIsAtDestination);
        }

        OutputBuilder.buildOutput(fileName + ".out", vehicles);
    }
}
