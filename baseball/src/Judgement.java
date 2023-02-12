import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Judgement {
    public static ArrayList<Integer> answer;

    public Judgement(ArrayList<Integer> answer) {
        this.answer = answer;
    }
    public static ArrayList<Integer> judge(int[] guess) {
        int[] result = new int[Game.STRIKE_CRITERIA];

        for (int i = 0; i < Game.STRIKE_CRITERIA; i++) {
            int checkResult = check(i, guess[i]);
            result[checkResult]++;
        }
        IO.printGuessResult(result);

        return (ArrayList<Integer>) Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    private static int check(int idx, int guess) {
        for (int i = 0; i < Game.STRIKE_CRITERIA; i++) {
            if (answer.get(i) == guess) return idx == i ? Game.STRIKE : Game.BALL;
        }
        return Game.MISS;
    }
}
