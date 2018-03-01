import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VehicleBuilder {
    public static List<Vehicle> buildVehicles(int numberOfVerhicles) {
        return IntStream.range(0, numberOfVerhicles).mapToObj(Vehicle::new).collect(Collectors.toList());
    }
}
