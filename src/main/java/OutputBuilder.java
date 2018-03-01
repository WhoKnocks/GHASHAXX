import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class OutputBuilder {

    public static void buildOutput(String outputFile, List<Vehicle> vehicleList) {
        List<String> outputLines = vehicleList.stream()
                .map(OutputBuilder::makeLine)
                .collect(Collectors.toList());
        IOUtil.writeLines(outputFile, outputLines);
    }

    private static String makeLine(Vehicle vehicle) {
        String rides = vehicle.getPreviousRides().stream().map(ride -> ((Integer) ride.getIndex()).toString()).collect(joining(" "));
        return "" + vehicle.getPreviousRides().size() + " " + rides;
    }
}
