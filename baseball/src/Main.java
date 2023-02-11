import java.util.*;

public class Main {
    private static final int STRIKE_CRITERIA = 3;

    private static final List<Integer> DIGITS = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

    private static int[] generateHitNumbers() {
        int[] hitNumbers = new int[STRIKE_CRITERIA];
        return Arrays.stream(hitNumbers).map(hitNumber -> {
            int idx = (int)(Math.random() * DIGITS.size());
            return DIGITS.remove(idx);
        }).toArray();

    }
    public static void main(String[] args) {
        Arrays.stream(generateHitNumbers()).forEach(System.out::println);
    }
}