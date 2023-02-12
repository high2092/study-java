import java.util.*;

public class Main {
    private static final int STRIKE_CRITERIA = 3;
    public static final int MISS = 0;
    public static final int STRIKE = 1;
    public static final int BALL = 2;

    private static Player player;
    private static boolean isGameOver = false;
    private static final int[] hitNumbers = generateHitNumbers();

    private static int[] generateHitNumbers() {
        List<Integer> DIGITS = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        int[] hitNumbers = Arrays.stream(new int[STRIKE_CRITERIA]).map(hitNumber -> {
            int idx = (int)(Math.random() * DIGITS.size());
            return DIGITS.remove(idx);
        }).toArray();

        return hitNumbers;
    }

    private static int check(int idx, int guess) {
        for (int i = 0; i < STRIKE_CRITERIA; i++) { // for문을 forEach로 대체하는 것의 장점이 떠오르지 않는 경우도 종종 맞닥뜨림
            if (hitNumbers[i] == guess) return idx == i ? STRIKE : BALL;
        }
        return MISS;
    }

    private static int[] checkNumbers(int[] guess) {
        int[] result = new int[STRIKE_CRITERIA];
        for (int i = 0; i < STRIKE_CRITERIA; i++) {
            int checkResult = check(i, guess[i]);
            result[checkResult]++;
        }

        IO.printGuessResult(result);
        return result;
    }

    private static boolean playTurn() {
        IO.printPlayTurnText();
        int[] inputNumbers = IO.inputNumbers(STRIKE_CRITERIA);
        int[] guessResultCount = checkNumbers(inputNumbers);

        return guessResultCount[STRIKE] == STRIKE_CRITERIA;
    }

    public static void main(String[] args) {
        player = IO.register();
        while (isGameOver == false) {
            isGameOver = playTurn();
        }
    }
}