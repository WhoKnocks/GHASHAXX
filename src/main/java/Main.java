import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        run("a_example");
        System.out.println("a done");
//        run("b_should_be_easy");
//        System.out.println("b done");
//        run("c_no_hurry");
//        System.out.println("c done");
//        run("d_metropolis");
//        System.out.println("d done");
//        run("e_high_bonus");
//        System.out.println("e done");
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

        List<Vehicle> vehicles = VehicleBuilder.buildVehicles(numberVehicles_F);

        for (int i = 0; 0 <= numberOfSteps_T; numberOfSteps_T--, i++) {

            while (!allVehiclesHaveRide(vehicles)) {
                for (Vehicle vehicle : vehicles) {
                    vehicle.takeRide(allRides, i, numberOfSteps_T, perRideBonus_B);
                }
            }

            for (Vehicle vehicle : vehicles) {
                vehicle.nextMove(i);
            }
            vehicles.forEach(Vehicle::checkIsAtDestination);
        }

        OutputBuilder.buildOutput(fileName + ".out", vehicles);
    }


    public static boolean allVehiclesHaveRide(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCurrentRide() == null) {
                return false;
            }
        }
        return true;
    }
}
