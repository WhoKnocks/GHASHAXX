import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        List<List<Integer>> lines = IOUtil.getLines("test.in", " ", Integer::parseInt);

        List<Integer> firstRow = lines.get(0);

        int numberOfRows_R = firstRow.get(0);
        int numberOfColumns_C = firstRow.get(1);
        int numberVehicles_F = firstRow.get(2);
        int numberOfRides_N = firstRow.get(3);
        int perRideBonus_B = firstRow.get(4);
        int numberOfSteps_T = firstRow.get(5);

        List<Ride> rides = lines.subList(1, lines.size() - 1).stream()
                .map(row -> new Ride(row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)))
                .collect(toList());


//        List<String> result = lines.stream()
//                .map(list -> list.stream()
//                        .map(Object::toString)
//                        .collect(Collectors.joining(" ")))
//                .collect(toList());
//
//        result.forEach(System.out::println);
//
//        IOUtil.writeLines("test.out", result);
    }
}
