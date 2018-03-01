import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<List<Integer>> lines = IOUtil.getLines("a_example.in", " ", Integer::parseInt);

        List<Integer> firstRow = lines.get(0);

        int numberOfRows_R = firstRow.get(0);
        int numberOfColumns_C = firstRow.get(1);
        int numberVehicles_F = firstRow.get(2);
        int numberOfRides_N = firstRow.get(3);
        int perRideBonus_B = firstRow.get(4);
        int numberOfSteps_T = firstRow.get(5);

        int rideIndex = 0;
        List<Ride> allRides = new ArrayList<>();
        for (List<Integer> row : lines.subList(1, lines.size() - 1)) {
            allRides.add(new Ride(rideIndex++, row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)));
        }

        List<Vehicle> vehicles = VehicleBuilder.buildVehicles(numberVehicles_F);

        for (int i = 0; i <= numberOfSteps_T; numberOfSteps_T--) {
            vehicles.forEach(vehicle -> vehicle.takeRide(allRides));
            vehicles.forEach(Vehicle::nextMove);
            vehicles.forEach(Vehicle::checkIsAtDestination);
        }

        OutputBuilder.buildOutput("output", vehicles);
    }
}
