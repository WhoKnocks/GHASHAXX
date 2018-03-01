import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        List<List<Integer>> lines = IOUtil.getLines("test.in", " ", Integer::parseInt);

        List<String> result = lines.stream()
                .map(list -> list.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" ")))
                .collect(toList());

        result.forEach(System.out::println);

        IOUtil.writeLines("test.out", result);
    }
}
