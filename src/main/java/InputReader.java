import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class InputReader<T> {

    public abstract T parseElement(String element);

    public List<List<T>> getLines(String file, String lineSplitter) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(InputReader.class.getResourceAsStream(file)));
            String line;
            List<List<T>> ret = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                ret.add(Arrays.stream(line.split(lineSplitter)).map(this::parseElement).collect(Collectors.toList()));
            }
            br.close();
            return ret;
        } catch (IOException ex) {
            throw new RuntimeException("Cannot read file " + file, ex);
        }
    }
}
