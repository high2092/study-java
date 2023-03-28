import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnswerGenerator {
    public static ArrayList<Integer> generate() {
        List<Integer> DIGITS = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        ArrayList<Integer> hitNumbers = Arrays.stream(new int[GameView.STRIKE_CRITERIA]).map(hitNumber -> {
            int idx = (int)(Math.random() * DIGITS.size());
            return DIGITS.remove(idx);
        }).collect(ArrayList::new, List::add, List::addAll);

        return hitNumbers;
    }
}
