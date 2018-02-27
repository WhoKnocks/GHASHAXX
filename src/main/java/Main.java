public class Main {

    public static void main(String[] args) {
        final InputReader<Integer> inputReader = new InputReader<Integer>() {
            @Override
            public Integer parseElement(String element) {
                return Integer.parseInt(element);
            }
        };

        inputReader.getLines("test.in", " ").forEach(integers -> {
            integers.forEach(integer -> System.out.print(" "+ integer));
            System.out.println();
        });
    }
}
