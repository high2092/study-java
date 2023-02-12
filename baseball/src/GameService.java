import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameService {
    public static ArrayList<Integer> answer;

    public GameService(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public boolean playTurn(InputValues inputNumbers) {
        ArrayList<Integer> guessResultCount = GameService.judge(inputNumbers);
        return guessResultCount.get(GameView.STRIKE) == GameView.STRIKE_CRITERIA;
    }

    public static ArrayList<Integer> judge(InputValues inputValues) {
        int[] result = new int[GameView.STRIKE_CRITERIA];

        for (int i = 0; i < GameView.STRIKE_CRITERIA; i++) {
            int checkResult = check(i, inputValues.toList().get(i));
            result[checkResult]++;
        }
        IO.printGuessResult(result);

        return (ArrayList<Integer>) Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    private static int check(int idx, int guess) {
        for (int i = 0; i < GameView.STRIKE_CRITERIA; i++) {
            if (answer.get(i) == guess) return idx == i ? GameView.STRIKE : GameView.BALL;
        }
        return GameView.MISS;
    }
}
